## Rabbit MQ for Asynchronous calls

RabbitMQ is a message broker. In essence, it accepts messages from producers, and delivers them to consumers. In-between, it can route, buffer, and persist the messages according to rules you give it.

The way RabbitMQ routes messages depends upon the messaging protocol it implements. RabbitMQ supports multiple messaging protocols. However, the one we are interested in is AMQP. It is an acronym for Advanced Message Queuing Protocol.

### Advanced Message Queuing Protocol

The conceptual model of AMQP is quite simple and straightforward. It has three entities:

1.Queue
2.Binding
3.Exchange

When a publisher pushes a message to RabbitMQ, it first arrives at an exchange. The exchange then distributes copies of these messages to variously connected queues. Finally, consumers receive these messages.

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/1.png)

### Producer:
Producer will send messages to RabbitMQ Exchanges with a routingKey(queuename). RabbitMQ uses routingKey(queuename) to determine which queues for routing messages.

### Queue:
Here, our message will be stored. Once consumed by consumers, message will be removed from queue.
RabbitMQ queues also follow FIFO (First-In-First-Out methodology).

### Consumer:
Consumer listens on a RabbitMQ Queue to receive messages.

### Bindings:
Bindings are the rules that a queue defines while establishing a connection with an exchange. You can have a queue connected to multiple exchanges. Every queue is also connected to a default exchange. An exchange will use these bindings to route messages to queues.

### Exchange
An Exchange is a gateway to RabbitMQ for your messages. The distance the message has to travel inside RabbitMQ depends on the type of exchange. 

### Primarily there are four types of exchanges

* Direct Exchange - It routes messages to a queue by matching routing key equal to binding key.  Routing key == Binding key
* Fanout Exchange - It ignores the routing key and sends message to all the available queues.
* Topic Exchange - It routes messages to multiple queues by a partial matching of a routing key. It uses patterns to match the routing and binding key. Routing key == Pattern in binding key.
* Headers Exchange - It uses message header instead of routing key.
* Default(Nameless) Exchange - It routes the message to queue name that exactly matches with the routing key.


### Local Environment Configuration

### How to install RabbitMQ in your local?

* Download supporting ERLANG component from here  and install.
* Download Rabbit MQ from here and install
* Enable management plugin using below command
  C:\Program Files\RabbitMQ Server\rabbitmq_server-3.7.17\sbin  > rabbitmq-plugins enable rabbitmq_management
* Login into Rabbit MQ browser using URL http://localhost:15672 using userid: guest and password: guest

  ![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/2.png)
* Now click on Queues Tab and create queue named praveenmq.

  ![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/3.png)
* Now click on Exchanges Tab and create exchange named praveenexchange and bind it to praveenmq.

  ![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/4.png)

### Add below dependency in pom.xml

```XML

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>

```

### Lets configure the above RabbitMQ properties in application of praveen-user-management-service
 
```YAML

rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

praveenoruganti-rabbitmq-integration-service:         
          rabbitmq:
            queueName: praveenmq
            topicExchange: praveenexchange

```

### Let's create the producer and consumer components for RabbitMQ in praveen-rabbitmq-integration-service
  
```JAVA

package com.praveen.rabbitmq.producer;

import java.time.LocalDate;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageProducer {

	@Autowired
	  private AmqpTemplate amqpTemplate;

	@Value("${praveenoruganti-rabbitmq-integration-service.rabbitmq.queueName}")
	private String queueName;

	@Value("${praveenoruganti-rabbitmq-integration-service.rabbitmq.topicExchange}")
	private String topicExchange;

	public void produceMsg(String msg){
		log.info(LocalDate.now().toString());
	    amqpTemplate.convertAndSend(topicExchange, queueName, msg);
	    log.info("Send msg = " + msg);
	    log.info(LocalDate.now().toString());
	  }
}

```

```JAVA

package com.praveen.rabbitmq.listener;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.praveen.rabbitmq.dao.PraveenLogDAO;
import com.praveen.rabbitmq.model.PraveenLog;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener {
	@Autowired
	private PraveenLogDAO praveenLogDAO;

	@RabbitListener(queues = "${praveen-rabbitmq-integration-service.rabbitmq.queueName}")
	public void receivedMessage(String message) {
		log.info(LocalDate.now().toString());
		try {
			Thread.sleep(5000);
			PraveenLog praveenLog = new PraveenLog();
			praveenLog.setLogMessage(message);
			praveenLog.setLogDate(Date.valueOf(LocalDate.now()));
			praveenLogDAO.createLog(praveenLog);
			log.info("Message Received:" + message);
			log.info(LocalDate.now().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

```

### Now open URL http://localhost:9100/swagger-ui.html

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/5.png)

Now I pushed the message Praveen Oruganti to Queue

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/6.png)

### Log information of Message Producer component and it was successfully pushed to praveenmq Queue

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/7.png)

###  I see Message Listener fetched the message from praveenmq Queue and pushed to mysql db for tracking purpose.

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/8.png)

### I am able to see the message pushed by Message Listener to mysqldb

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/9.png)

### Let's see the message in mysql db

### SELECT * from PRAVEENLOG

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/10.png)

### PCF Configuration For RabbitMQ

### All other code and configurations will be similar to Local Environment changes except we will use CloudAMQP instead of local rabbitmq.

### Let's start creating the PCF Service Rabbit MQ

* Click on ADD A SERVICE button and search for Rabbit MQ in Market Place

  Provide the name of the service as praveen-rabbitmq and bind to the praveen-user-management-service.

  ![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/11.png)

* Click on Manage button and then click on RabbitMQ Manager button

  ![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/12.png)

  ![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/13.png)

* Now click on Queues Tab and create queue named praveenmq

  ![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/14.png)

* Now click on Exchanges Tab and create exchange named praveenexchange and bind it to praveenmq

  ![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-rabbitmq-integration-service/src/main/resources/images/15.png)






