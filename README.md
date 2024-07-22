
# Spring Kafka Stream Avro Example

A sample springboot project using spring-kafka, KStream, KTable and avro as message template.

## Using


* Java 21
* Spring Boot 3.3.0
* Confluent Kafka 7.6.1
* Confluent Schema Registry 7.6.1
* Avro 1.11.3
* Spring Kafka 3.1.4
* Kafka-Stream 3.6.2
* kafka-streams-avro-serde 7.6.1
## Modules

1) `avro-models`
   Module for Avro files and Java Generated Code using avro maven plugin

2) `product-avro-producer`
   Spring Kafka Avro Producer for Product

3) `unitprice-avro-producer`
   Spring Kafka Avro Producer for Unit Price 

4) `product-avro-consumer`
   Spring Kafka Avro Consumer for Product and Unit Price

5) `kafka-stream-avro`
   Spring Kafka Stream Avro for updated Products by doing a lookup on Unit Price


## Goal

Goal of this example project is to use Kafka Stream to update incoming Products messages with new unit price by doing a lookup on Unit Price KTable.

* `main` branch - complete code example

## Build to the project

```
./mvnw clean install
```
## Start / Stop Kafka & Zookeeper

```
docker-compose up -d
```

```
docker-compose down -v
```
## Start both producer and consumer

* Spring Boot application: `product-avro-consumer`

```
java -jar product-avro-consumer/target/product-avro-consumer-0.0.1-SNAPSHOT.jar
```

* Spring Boot application: `unitprice-avro-producer`

```
java -jar unitprice-avro-producer/target/unitprice-avro-producer-0.0.1-SNAPSHOT.jar
```

* Spring Boot application: `kafka-stream-avro`

```
java -jar kafka-stream-avro/target/kafka-stream-avro-0.0.1-SNAPSHOT.jar
```

* Spring Boot application: `product-avro-producer`

```
java -jar product-avro-producer/target/product-avro-producer-0.0.1-SNAPSHOT.jar
```