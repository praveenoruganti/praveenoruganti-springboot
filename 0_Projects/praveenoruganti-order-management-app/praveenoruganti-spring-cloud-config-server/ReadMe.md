## Spring Cloud Config Server

Instead of having properties or yml in each springboot application jar, it will be good if we have remote configurations for our applications and then spring boot application access those.

### Local Environment Configuration

Create a springboot application with application name praveen-spring-cloud-config-server

### Add below dependency in pom.xml

```XML

<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-config-server</artifactId>
</dependency>	

```
### Include @EnableConfigServer on top of main class

```JAVA

package com.praveen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class PraveenSpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraveenSpringCloudConfigServerApplication.class, args);
	}

}

```

Include the below GIT path where the application properties are placed in application.yml

```YAML

spring:
 application: 
  name: praveenorugantitech-spring-cloud-config-server

 cloud:
   config:
     server:
       git:
        uri: https://github.com/praveenorugantitech/praveenorugantitech-springboot/tree/master/0_Projects/praveen-spring-config-server-master
        searchPaths: config
server:
 port: 8888


```


### PCF Configuration of Config Server service

While using PCF there is no need to develop separate spring cloude config server springboot application rather we will use the PCF inbuilt Config Server service.

### Login into PCF and go to market place and select Config server service

![screenshot of the app](https://raw.githubusercontent.com/praveenorugantitech/praveenorugantitech-springboot/master/0_Projects/praveenorugantitech-spring-cloud-config-server/src/main/resources/images/1.png)

Then click on Settings present in the left tab

![screenshot of the app](https://raw.githubusercontent.com/praveenorugantitech/praveenorugantitech-springboot/master/0_Projects/praveenorugantitech-spring-cloud-config-server/src/main/resources/images/2.png)

Then click on Add Parameter and select Enter as JSON ON and then provide the git configuration properties and click on Update Configuration Parameters button.

![screenshot of the app](https://raw.githubusercontent.com/praveenorugantitech/praveenorugantitech-springboot/master/0_Projects/praveenorugantitech-spring-cloud-config-server/src/main/resources/images/3.png)

Then check whether the git information is properly updated by clicking Manage button.


![screenshot of the app](https://raw.githubusercontent.com/praveenorugantitech/praveenorugantitech-springboot/master/0_Projects/praveenorugantitech-spring-cloud-config-server/src/main/resources/images/4.png)


![screenshot of the app](https://raw.githubusercontent.com/praveenorugantitech/praveenorugantitech-springboot/master/0_Projects/praveenorugantitech-spring-cloud-config-server/src/main/resources/images/5.png)

### [Buy me a Book](https://bit.ly/388sUbE)




