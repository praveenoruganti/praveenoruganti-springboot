## Microservice Registry and Discovery

### Local Environment Using Netflix Eureka

Eureka Server is an application that holds the information about all client-service applications. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address. Eureka Server is also known as Discovery Server.
Normally in Micro Service Architecture Design we are developing separate Services and exposing each API as service Endpoint and whenever we required to access other services in simple we will be using RestTemplate.

It is highly impossible to remember the microservice restful service endpoint URL’s with hostname and port so Netflix team came up with solution with Eureka server where all services endpoints will be registered.

### Registering with Eureka

When a client registers with Eureka, it provides meta-data about itself such as host and port, health indicator URL, homepage etc. Eureka receives heartbeat messages from each instance belonging to a service. If the heartbeat fails over a configurable timetable, the instance is normally removed from the registry.

### In local, we need to develop eureka server SpringBoot application

We need to add below eureka server dependency in pom.xml


```XML

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>	

```

We need to include @EnableEurekaServer on top of main class

```JAVA

package com.praveen.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaServerApplication.class, args);
	}

}

```

We need to configure below eureka server properties in application.yml


```YAML

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
server:
  port: 8761
spring:
  application:
    name: praveenoruganti-spring-cloud-eureka-server

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS

```

### Let’s Open URL for eureka server application

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-spring-cloud-eureka-server/master/praveenoruganti-spring-cloud-eureka-server/src/main/resources/images/1.png)

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-spring-cloud-eureka-server/master/praveenoruganti-spring-cloud-eureka-server/src/main/resources/images/2.png)

If you see above there are no application registered in eureka server.

### Let’s consider an example, we have 2 microservices i.e... Flipkart Order Management Service and Flipkart Billing service.

Here the flow will be Flipkart Order Management Service will call Flipkart Billing Service for successful creation of order based on orderid. Here we will call the service using RestTemplate.

### praveen-flipkart-ordermanagement-service

### We need to add the below dependency in pom.xml

```XML

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

```

### We need to add @EnableEurekaClient on top of main class

```JAVA

package com.praveen.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlipkartBillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipkartBillingServiceApplication.class, args);
	}	
}

```

### We need to configure eureka properties in application.yml file

```YAML

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS
eureka:
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
  instance:
    hostname: localhost
server:
  port: 8091
spring:
  application:
    name: praveenoruganti-flipkart-billing-service    

```

### praveen-flipkart-ordermanagement-service

### We need to add the below dependency in pom.xml

```XML

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

```

### We need to add @EnableEurekaClient on top of main class


```JAVA

package com.praveen.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FlipkartOrderManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipkartOrderManagementServiceApplication.class, args);
	}
}

```

### We need to configure eureka properties in application.yml file

```YAML

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS
        

eureka:
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
  instance:
    hostname: localhost
server:
  port: 8090
spring:
  application:
    name: praveenoruganti-flipkart-ordermanagement-service
    
praveen-flipkart-ordermanagement-service:
                   billingURL: http://praveenoruganti-flipkart-billing-service/rest/billingservice/billingorder


```

As we have discussed earlier, praveenoruganti-flipkart-ordermanagement-service is interacting with praveenoruganti-flipkart-billing-service with the help of rest template and let’s see the code of it.

```JAVA

public String createOrder(String orderid) throws Exception {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>(orderid, headers);
			// call billing service
			String msg = restTemplate.exchange(billingURL, HttpMethod.POST, entity, String.class).getBody();
			return msg;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

```

Here if you see billingURL is mentioned in application.yml as http://praveenoruganti-flipkart-billing-service/rest/billingservice/billingorder 
As we have used Eureka Server, there is no need for providing the hostname and port for discovery as praveenoruganti-flipkart-ordermanagement-service and praveenoruganti-flipkart-billing-service are already registered in Eureka server rather we have used the applicationname in the URL for service interaction via rest template.


### Let’s open URL for eureka server application

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-spring-cloud-eureka-server/master/praveenoruganti-spring-cloud-eureka-server/src/main/resources/images/3.png)
![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-spring-cloud-eureka-server/master/praveenoruganti-spring-cloud-eureka-server/src/main/resources/images/6.png)
### PCF configuration for service registry and discovery

While using PCF there is no need to develop separate Netflix eureka server springboot application rather we will use the PCF inbuilt Service Registry service.

Login into PCF and go to market place and select Service Registry service.

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-spring-cloud-eureka-server/master/praveenoruganti-spring-cloud-eureka-server/src/main/resources/images/5.png)

There is no need to use spring-cloud-starter-netflix-eureka-client dependency rather we will be including spring-cloud-services-starter-service-registry dependency in pom.xml

```XML

 <dependency>
    <groupId>io.pivotal.spring.cloud</groupId>
    <artifactId>spring-cloud-services-starter-service-registry</artifactId>
</dependency>

```

All other configurations which we discussed above related to local environment using eureka server holds good.

