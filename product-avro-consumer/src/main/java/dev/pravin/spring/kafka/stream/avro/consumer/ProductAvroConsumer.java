package dev.pravin.spring.kafka.stream.avro.consumer;

import dev.pravin.avro.product.Product;
import dev.pravin.avro.product.UnitPrice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_PARTITION;

@Slf4j
@Component
public class ProductAvroConsumer {

    static final String PRODUCT_TOPIC_NAME = "product-data-avro";

    static final String UNITPRICE_TOPIC_NAME = "unitprice-data-avro";

    private static final String UPDATED_PRODUCT_TOPIC_NAME = "updated-product-data-avro";

    @KafkaListener(topics = PRODUCT_TOPIC_NAME)
    public void listenProduct(Product product, @Header(RECEIVED_PARTITION) Integer partitionId) {
        log.info("Consumed Product : {}, {}, {}, {}, {}, from partition: {}",
                product.getProductId(), product.getName(),
                product.getDescription(), product.getUnitPrice()
                ,product.getEffectiveFrom(), partitionId);
    }

    @KafkaListener(topics = UNITPRICE_TOPIC_NAME)
    public void listenUnitPrice(UnitPrice unitPrice, @Header(RECEIVED_PARTITION) Integer partitionId) {
        log.info("Consumed UnitPrice: {}, {}, {}, from partition: {}",
                unitPrice.getProductId(), unitPrice.getUnitPrice()
                ,unitPrice.getEffectiveFrom(), partitionId);
    }

    @KafkaListener(topics = UPDATED_PRODUCT_TOPIC_NAME)
    public void listenKStreamProduct(Product product, @Header(RECEIVED_PARTITION) Integer partitionId) {
        log.info("Consumed Updated Product : {}, {}, {}, {}, {}, from partition: {}",
                product.getProductId(), product.getName(),
                product.getDescription(), product.getUnitPrice()
                ,product.getEffectiveFrom(), partitionId);
    }
}
