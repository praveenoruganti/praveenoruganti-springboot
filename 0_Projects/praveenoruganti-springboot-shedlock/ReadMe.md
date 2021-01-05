## Praveen Oruganti SpringBoot Shedlock

We have seen the [Spring Boot Batch scheduler job creation](https://github.com/praveenorugantitech/praveenorugantitech-springboot/tree/master/0_Projects/praveenorugantitech-springboot-batch-scheduler) earlier. Most of us probably faced with a use case to make only one specific cron job to run at a time in a distributed environment. For example; we don’t want to run multiple cron jobs to send same email to customers or charging them multiple times. To avoid this we have to find a way to make a cron job run only in one instance. For this 

Lets see how to use Shedlock which provides a distributed lock mechanism for cron jobs. Well for this we also need to have a database so I will use MySQL in this example to run locally.


### Add the shedlock dependency in pom.xml

```XML

<dependency>
	<groupId>net.javacrumbs.shedlock</groupId>
	<artifactId>shedlock-spring</artifactId>
	<version>4.15.1</version>
</dependency>
<dependency>
	<groupId>net.javacrumbs.shedlock</groupId>
	<artifactId>shedlock-provider-jdbc-template</artifactId>
	<version>4.15.1</version>
</dependency>

```

### Enabling Shedlock

First step is creating a table for locks to be used by Shedlock.

```SQL

CREATE TABLE shedlock(
    name VARCHAR(64), 
    lock_until TIMESTAMP(3) NULL, 
    locked_at TIMESTAMP(3) NULL, 
    locked_by  VARCHAR(255), 
    PRIMARY KEY (name)
)

```
Later on, we have to add one annotation and a bean producer for LockProvider for enabling Shedlock. So, our Application class will look like;

### ShedlockApplication

```JAVA

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "${app.shedlock.defaultLockAtMostFor}")
public class ShedlockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShedlockApplication.class);
	}

	@Bean
	public LockProvider lockProvider(DataSource dataSource) {
	    return new JdbcTemplateLockProvider(dataSource);
	}
}

```
- EnableSchedulerLock annotation enables Shedlock in Spring context so that we can use it for our jobs. defaultLockAtMostFor attribute is required but it can be overridden by individual tasks. <br/>
- We need to create a Bean for LockProvider which will be used by Shedlock to update lock table we created. <br/>


### Running a Job with Shedlock

All jobs which need distributed lock should be annotated with @SchedulerLock annotation, otherwise, jobs will run in parallel. There are three main parameters to configure.

- name - Every job has to have a unique name. Remember that name field from shedlock table was the primary key
- lockAtLeastFor - This attribute is for ensuring the current job will hold the lock at least given amount of time. For example; if we configure it to 20 seconds then the lock won’t released even though job finished before 20 seconds.
- lockAtMostFor - This attribute is for making sure lock is released in case of executing instance dies. As it is suggested from the documentation itself it will be better to set this attribute larger than the maximum estimated execution time. If a task takes longer than this value than an unexpected behaviour might happen because some other instance will also run the same job since lock will be released.

I will create a job called QuoteGeneratorJob with lockAtLeastFor attribute set to 15 seconds and lockAtMostFor attribute set to 20 seconds. The actual time it will take to run will be just 5 seconds and it will run at every minute.

### ShedlockJobHandler

```JAVA
package com.praveen.shedlock.handler;

import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.praveen.shedlock.constants.Constants;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Component
@Slf4j
public class ShedlockJobHandler {

	private Environment env;

	public ShedlockJobHandler(Environment env) {
		this.env = env;
	}

	@Scheduled(cron = "${app.shedlock.scheduler.configCornJob}", zone = "${app.shedlock.scheduler.timezone}")
	@SchedulerLock(name = "${app.shedlock.name}", lockAtLeastFor = "${app.shedlock.lockAtLeastFor}", lockAtMostFor = "${app.shedlock.lockAtMostFor}")
	public void quoteGeneratorJob() throws InterruptedException {
		log.info("quoteGeneratorJob Starts");
		String runnableEnv = env.getProperty("app.shedlock.scheduler.runnableEnvironment");
		String randomQuote = Constants.quotes[(int) (Math.random() * Constants.quotes.length)];
		if (runnableEnv.equalsIgnoreCase("dev")) {
			System.out.println(randomQuote);
		}
		log.info("quoteGeneratorJob Ends");
	}
}

```

### Constants

```JAVA

package com.praveen.shedlock.constants;

public class Constants {
	public static final String[] quotes = new String[] {
			"You will face many defeats in life, but never let yourself be defeated.",
			"The greatest glory in living lies not in never falling, but in rising every time we fall.",
			"In the end, it's not the years in your life that count" };
}

```

### application.yml

``` YAML

server:
  port: 8080
spring:
  application:
    name: praveenorugantitech-springboot-shedlock
  jpa:
  database: MYSQL
  show-sql: true
  generate-ddl: false
  
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://root:password@localhost:3306/praveendb?reconnect=true
    username: root
    password: password
    hikari:
      connectionTimeout : 30000
      idleTimeout : 600000
      maxLifetime : 1800000
      maximumPoolSize : 5
management:
  endpoints:
   enable-by-default: false   
   web:
     base-path: /
     exposure:
       include: info,refresh,logger
  endpoint:    
    info:
      enabled: true
    refresh:
      enabled: true
    loggers:
      enabled: true
apidocs:
  info:
   description: |
   
       Praveen Oruganti SpringBoot Shedlock
             
   version: '1.0'
   title: Praveen Oruganti SpringBoot Shedlock
   termsOfService: 'urn:tos'
   contact:
     name: Praveen Oruganti
     url: https://praveenorugantitech.blogspot.com
     email: praveenorugantitech@gmail.com

app:
 disable:
  tryOutOption:false 
 serviceName: Praveen Oruganti SpringBoot Shedlock
 shedlock: 
   name: QuoteGeneratorJob
   defaultLockAtMostFor: 30s
   lockAtLeastFor: 15s
   lockAtMostFor: 20s
   scheduler:
     configCornJob: 0 0/1 * * * ?
     runnableEnvironment: dev
     timezone: IST

     
```

### Here with the application logs

```LOGS

"@timestamp":"2020-11-08T01:03:00.455+05:30","severity":"INFO","service":"praveenorugantitech-springboot-shedlock","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.p.shedlock.handler.ShedlockJobHandler","methodName":"quoteGeneratorJob","lineNo":"29","message":"quoteGeneratorJob Starts","stacktrace":""}
You will face many defeats in life, but never let yourself be defeated.
{"@timestamp":"2020-11-08T01:03:00.459+05:30","severity":"INFO","service":"praveenorugantitech-springboot-shedlock","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.p.shedlock.handler.ShedlockJobHandler","methodName":"quoteGeneratorJob","lineNo":"35","message":"quoteGeneratorJob Ends","stacktrace":""}
{"@timestamp":"2020-11-08T01:04:00.126+05:30","severity":"INFO","service":"praveenorugantitech-springboot-shedlock","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.p.shedlock.handler.ShedlockJobHandler","methodName":"quoteGeneratorJob","lineNo":"29","message":"quoteGeneratorJob Starts","stacktrace":""}
In the end, it's not the years in your life that count
{"@timestamp":"2020-11-08T01:04:00.127+05:30","severity":"INFO","service":"praveenorugantitech-springboot-shedlock","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.p.shedlock.handler.ShedlockJobHandler","methodName":"quoteGeneratorJob","lineNo":"35","message":"quoteGeneratorJob Ends","stacktrace":""}
{"@timestamp":"2020-11-08T01:05:00.155+05:30","severity":"INFO","service":"praveenorugantitech-springboot-shedlock","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.p.shedlock.handler.ShedlockJobHandler","methodName":"quoteGeneratorJob","lineNo":"29","message":"quoteGeneratorJob Starts","stacktrace":""}
The greatest glory in living lies not in never falling, but in rising every time we fall.
{"@timestamp":"2020-11-08T01:05:00.157+05:30","severity":"INFO","service":"praveenorugantitech-springboot-shedlock","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.p.shedlock.handler.ShedlockJobHandler","methodName":"quoteGeneratorJob","lineNo":"35","message":"quoteGeneratorJob Ends","stacktrace":""}

```

If you see the table only one instance will hold the lock.

![screenshot of the app](https://raw.githubusercontent.com/praveenorugantitech/praveenorugantitech-springboot/master/0_Projects/praveenorugantitech-springboot-shedlock/src/main/resources/images/1.PNG)

### [Buy me a Book](https://www.buymeacoffee.com/praveenoruganti)


