package dev.pravin.spring.kafka.stream.avro.config;

import dev.pravin.avro.product.Product;
import dev.pravin.avro.product.UnitPrice;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroDeserializer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import java.util.Collections;
import java.util.Map;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    private static final String PRODUCT_TOPIC_NAME = "product-data-avro";
    private static final String UNITPRICE_TOPIC_NAME = "unitprice-data-avro";
    private static final String UPDATED_PRODUCT_TOPIC_NAME = "updated-product-data-avro";

    private CustomValueJoiner customValueJoiner;

    public KafkaStreamConfig(CustomValueJoiner customValueJoiner) {
        this.customValueJoiner = customValueJoiner;
    }

    @Bean
    public KStream<String, Product> kStream(StreamsBuilder kStreamBuilder) {

        final Map<String, String> serdeConfig = Collections.singletonMap("schema.registry.url",
                "http://localhost:8081");

        Serde<String> stringSerde = Serdes.String();
        stringSerde.configure(serdeConfig, true); // `true` for record keys

        //Unit Price value Serdes
        Serde<UnitPrice> unitPriceSerde = Serdes.serdeFrom( new SpecificAvroSerializer<>(), new SpecificAvroDeserializer<>());
        unitPriceSerde.configure(serdeConfig, false); // `false` for record values

        //Unit Price KTable
        KTable<String, UnitPrice> unitPriceKTable = kStreamBuilder.table(UNITPRICE_TOPIC_NAME, Consumed.with( stringSerde, unitPriceSerde));
        unitPriceKTable.toStream().print( Printed.toSysOut());

        //Product value Serdes
        Serde<Product> productSerde = Serdes.serdeFrom( new SpecificAvroSerializer<>(), new SpecificAvroDeserializer<>());
        productSerde.configure(serdeConfig, false); // `false` for record values

        //Product KStream
        KStream<String, Product> productKStream = kStreamBuilder.stream( PRODUCT_TOPIC_NAME, Consumed.with( stringSerde, productSerde));
        productKStream.print( Printed.toSysOut());

        //Joining Product KStream with Unit Price KTable
        KStream<String, Product> updatedProductKStream = productKStream.join(unitPriceKTable, customValueJoiner,
                Joined.with( stringSerde, productSerde, unitPriceSerde));

        updatedProductKStream.print( Printed.toSysOut());

        System.out.println("Publishing to updated product topic: " + UPDATED_PRODUCT_TOPIC_NAME);
        updatedProductKStream.to( UPDATED_PRODUCT_TOPIC_NAME, Produced.with( stringSerde, productSerde ) );

        return updatedProductKStream;
    }
}
