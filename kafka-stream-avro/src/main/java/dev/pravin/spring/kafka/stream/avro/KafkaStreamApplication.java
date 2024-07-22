package dev.pravin.spring.kafka.stream.avro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KafkaStreamApplication {

    public static void main(String[] args) {
        System.out.println( "Inside KafkaStreamApplication main" );
        SpringApplication.run( KafkaStreamApplication.class, args );
    }
}
