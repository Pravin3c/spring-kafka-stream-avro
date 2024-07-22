package dev.pravin.spring.kafka.stream.avro.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import dev.pravin.avro.product.UnitPrice;

@Slf4j
@Component
public class UnitPriceAvroProducer {

    static final String TOPIC_NAME = "unitprice-data-avro";

    private final KafkaTemplate<String, UnitPrice> kafkaTemplate;

    public UnitPriceAvroProducer(KafkaTemplate<String, UnitPrice> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(UnitPrice unitPrice) {
        log.info("Producing Unit Price Data: {}, {}, {} in topic {}", unitPrice.getProductId(),
                unitPrice.getUnitPrice(), unitPrice.getEffectiveFrom(), TOPIC_NAME);
        kafkaTemplate.send(TOPIC_NAME, String.valueOf(unitPrice.getProductId()), unitPrice);
    }
}
