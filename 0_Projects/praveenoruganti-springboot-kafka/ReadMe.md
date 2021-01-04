## Apache Kafka for Asynchronous Calls

### Apache Kafka
Apache Kafka is a distributed streaming platform with capabilities such as publishing and subscribing to a stream of records, storing the records in a fault tolerant way, and processing that stream of records. 
It is used to build real-time streaming data pipelines, that can perform functionalities such as reliably passing a stream of records from one application to another and processing and transferring the records to the target applications.

### Topics
Kafka is run as a cluster in one or more servers and the cluster stores/retrieves the records in a feed/category called Topics. Each record in the topic is stored with a key, value, and timestamp.
The topics can have zero, one, or multiple consumers, who will subscribe to the data written to that topic. In Kafka terms, topics are always part of a multi-subscriber feed.

### Partitions
The Kafka cluster uses a partitioned log for each topic.

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-kafka/src/main/resources/images/1.png)

The partition maintains the order in which data was inserted and once the record is published to the topic, it remains there depending on the retention period (which is configurable). The records are always appended at the end of the partitions. It maintains a flag called 'offsets,' which uniquely identifies each record within the partition.
The offset is controlled by the consuming applications. Using offset, consumers might backtrace to older offsets and reprocess the records if needed.

### Producers
The stream of records, i.e. data, is published to the topics by the producers. They can also assign the partition when it is publishing data to the topic. The producer can send data in a round robin way or it can implement a priority system based on sending records to certain partitions based on the priority of the record.

### Consumers
Consumers consume the records from the topic. They are based on the concept of a consumer-group, where some of the consumers are assigned in the group. The record which is published to the topic is only delivered to one instance of the consumer from one consumer-group. Kafka internally uses a mechanism of consuming records inside the consumer-group. Each instance of the consumer will get hold of the particular partition log, such that within a consumer-group, the records can be processed parallelly by each consumer.

### Spring Boot Kafka Application
Spring provides good support for Kafka and provides the abstraction layers to work with over the native Kafka Java clients.

### We can add the below dependencies to get started with Spring Boot and Kafka.

```XML

<dependency>
	<groupId>org.springframework.kafka</groupId>
	<artifactId>spring-kafka</artifactId>
</dependency>

```

### Steps to download and run Apache Kafka

Step 1: Download the apache kafka and unzip using winrar
http://apachemirror.wuchna.com/kafka/2.3.0/kafka_2.12-2.3.0.tgz
Step 2: Start the server

### Once you download Kafka, you can issue a command to start ZooKeeper which is used by Kafka to store metadata. 
D:\Praveen\Softwares\kafka_2.12-2.3.0>bin\windows\zookeeper-server-start.bat config\zookeeper.properties

### Next, we need to start the Kafka cluster locally by issuing the below command.
D:\Praveen\Softwares\kafka_2.12-2.3.0>bin\windows\kafka-server-start.bat .\config\server.properties

### Now, by default, the Kafka server starts on localhost:9092.
Lets develop a simple REST controller with Swagger integration and expose with one endpoint, /publish, as shown below. It is used to publish the message to the topic. 

```JAVA

package com.praveen.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.kafka.service.ProducerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Apache Kafka Restful Service", value = "KafkaController", description = "Controller for Kafka Publishing")
@RequestMapping(value = "/rest/kafka")
public class KafkaController {

	@Autowired
	ProducerService producerService;

	@PostMapping(value = "/publish")
	@ApiOperation(value = "Publishing Message")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.producerService.sendMessage(message);
	}

}

```

We can then write the producer which uses Spring's KafkaTemplate to send the message to a topic named users, as shown below.

```JAVA

package com.praveen.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
	private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);
	private static final String TOPIC = "users";
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		logger.info(String.format("$$ -> Producing message --> %s", message));
		this.kafkaTemplate.send(TOPIC, message);
	}
}

```
We can also write the consumer as shown below, which consumes the message from the topic users and output the logs to the console.

```JAVA

package com.praveen.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
	private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

	@KafkaListener(topics = "users", groupId = "group_id")
	public void consume(String message) {
		logger.info(String.format("$$ -> Consumed Message -> %s", message));
	}
}

```

Now, we need a way to tell our application where to find the Kafka servers and create a topic and publish to it. We can do it using application.yaml as shown below. 

```YAML

server:
  port: 9091
# servlet:
#   contextPath: /praveen
spring:
  application:
    name: praveenoruganti-springboot-kafka
  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      group-id: group-id
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    producer:
      bootstrap-servers: localhost:9092
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer


```

Lets see the main application class with Swagger integration

```JAVA
package com.praveen.kafka;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringbootKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootKafkaApplication.class, args);
	}

	@Bean
	public Docket configDock() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(basePackage("com.praveen.kafka.controller"))
				.paths(regex("/rest.*")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SpringBootKafka").description("WELCOME TO SWAGGER CLIENT")
				.contact(new Contact("PRAVEEN ORUGANTI", "https://praveenorugantitech.blogspot.com/",
						"praveenorugantitech@gmail.com"))
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
				.build();
	}

}

```

Now, if we run the application and hit the endpoint as shown below, we have published a message to the topic.

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-kafka/src/main/resources/images/2.png)

Now, if we check the logs from the console, it should print the message which was sent to the publish endpoint as seen below.


![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-kafka/src/main/resources/images/3.png)

### [Buy me a Book](https://bit.ly/388sUbE)





