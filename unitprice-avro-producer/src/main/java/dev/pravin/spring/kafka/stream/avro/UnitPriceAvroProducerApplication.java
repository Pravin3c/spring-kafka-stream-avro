package dev.pravin.spring.kafka.stream.avro;

import dev.pravin.avro.product.UnitPrice;
import dev.pravin.spring.kafka.stream.avro.producer.UnitPriceAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class UnitPriceAvroProducerApplication {

    @Autowired
    UnitPriceAvroProducer unitPriceAvroProducer;

    public static void main(String[] args) {
        System.out.println("Inside Producer main");
        SpringApplication.run(UnitPriceAvroProducerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void produce() {

        System.out.println("Inside Produce method");

        unitPriceAvroProducer.produce( UnitPrice.newBuilder().
                setProductId(1).
                setUnitPrice( 5.0 ).
                setEffectiveFrom( "2021-01-01" )
                .build()
        );

        unitPriceAvroProducer.produce( UnitPrice.newBuilder().
                setProductId(4).
                setUnitPrice( 4.0 ).
                setEffectiveFrom( "2024-01-01" )
                .build()
        );
    }
}
