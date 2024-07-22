package dev.pravin.spring.kafka.stream.avro.config;

import dev.pravin.avro.product.Product;
import dev.pravin.avro.product.UnitPrice;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.stereotype.Component;

@Component
public class CustomValueJoiner implements ValueJoiner<Product, UnitPrice, Product> {

    @Override
    public Product apply(Product product, UnitPrice unitPrice) {
        product.setUnitPrice(unitPrice.getUnitPrice());
        return product;
    }
}
