package dev.pravin.spring.kafka.stream.avro;

import dev.pravin.avro.product.Product;
import dev.pravin.spring.kafka.stream.avro.producer.ProductAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProductAvroProducerApplication {

    @Autowired
    ProductAvroProducer productAvroProducer;

    public static void main(String[] args) {
        System.out.println("Inside Producer main");
        SpringApplication.run(ProductAvroProducerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void produce() {

        System.out.println("Inside Produce method");

        productAvroProducer.produce(Product.newBuilder().
                setProductId(1).
                setName("Bread").
                setDescription("This is a Bread Product").
                setUnitPrice( 1.0 ).
                setEffectiveFrom( "2021-01-01" )
                .build()
        );

        productAvroProducer.produce(Product.newBuilder().
                setProductId(2).
                setName("Milk").
                setDescription("This is a Milk product").
                setUnitPrice( 2.0 ).
                setEffectiveFrom( "2021-07-01" )
                .build()
        );

        productAvroProducer.produce(Product.newBuilder().
                setProductId(3).
                setName("Tea").
                setDescription("This is a Tea product").
                setUnitPrice( 3.0 ).
                setEffectiveFrom( "2021-09-09" )
                .build()
        );
    }
}
