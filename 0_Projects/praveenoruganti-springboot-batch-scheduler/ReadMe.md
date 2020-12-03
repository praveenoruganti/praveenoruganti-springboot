## Praveen Oruganti SpringBoot Batch Scheduler


### Add the batch dependency in pom.xml

```XML

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-batch</artifactId>
</dependency>

```

### Cron expression

Cron expression is represented by six fields:

second, minute, hour, day of month, month, day(s) of week

![screenshot of the app](https://raw.githubusercontent.com/praveenoruganti/praveenoruganti-springboot/master/0_Projects/praveenoruganti-springboot-batch-scheduler/src/main/resources/images/1.PNG)

From the above cron expression, we give you some example patterns:

"0 0 * * * *" = the top of every hour of every day. <br />
"*/10 * * * * *" = every ten seconds. <br />
"0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day. <br />
"0 0 8,10 * * *" = 8 and 10 o'clock of every day. <br />
"0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day. <br />
"0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays <br />
"0 0 0 25 12 ?" = every Christmas Day at midnight <br />

### Configure batch job scheduler with spring boot

Configuring batch job scheduler very easy with spring boot, we just do two steps:

1. Enable scheduling with @EnableScheduling annotation. <br />
2. Create a method annotated with @Scheduled and a cron job pattern. <br />

Lets see the code implementation

### BatchSchedulerApplication

```JAVA

@SpringBootApplication
@EnableScheduling
public class BatchSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchSchedulerApplication.class);
	}
}
```

### BatchScheduler

```JAVA

package com.praveen.batch.scheduler;

import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.praveen.batch.constants.Constants;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BatchScheduler {

	private Environment env;

	public BatchScheduler(Environment env) {
		this.env = env;
	}

	@Scheduled(cron = "${app.scheduler.configCornJob}", zone = "${app.scheduler.timezone}")
	public void quoteGeneratorScheduler() {
		log.info("quoteGeneratorScheduler Starts");
		String runnableEnv = env.getProperty("app.scheduler.runnableEnvironment");
		String randomQuote = Constants.quotes[(int) (Math.random() * Constants.quotes.length)];
		if (runnableEnv.equalsIgnoreCase("dev")) {
			System.out.println(randomQuote);
		}
		log.info("quoteGeneratorScheduler Ends");
	}
}


```

### Constants

```JAVA

package com.praveen.batch.constants;

public class Constants {
	
	public static final String[] quotes = new String[] { "You will face many defeats in life, but never let yourself be defeated.",
			"The greatest glory in living lies not in never falling, but in rising every time we fall.",
			"In the end, it's not the years in your life that count" };

}

```

### application.yml

```YAML
app:
 scheduler:
   configCornJob: 0 0/1 * * * ?
   runnableEnvironment: dev
   timezone: IST
```

### If you see the above implementation, We have created quote generated scheduler which runs every 1 minute.

Lets see the Logs now.

``` LOG
{"@timestamp":"2020-11-08T00:23:00.016+05:30","severity":"INFO","service":"praveenoruganti-springboot-batch-scheduler","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.praveen.batch.scheduler.BatchScheduler","methodName":"quoteGeneratorScheduler","lineNo":"24","message":"quoteGeneratorScheduler Starts","stacktrace":""}
You will face many defeats in life, but never let yourself be defeated.
{"@timestamp":"2020-11-08T00:23:00.016+05:30","severity":"INFO","service":"praveenoruganti-springboot-batch-scheduler","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.praveen.batch.scheduler.BatchScheduler","methodName":"quoteGeneratorScheduler","lineNo":"35","message":"quoteGeneratorScheduler Ends","stacktrace":""}
{"@timestamp":"2020-11-08T00:24:00.010+05:30","severity":"INFO","service":"praveenoruganti-springboot-batch-scheduler","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.praveen.batch.scheduler.BatchScheduler","methodName":"quoteGeneratorScheduler","lineNo":"24","message":"quoteGeneratorScheduler Starts","stacktrace":""}
The greatest glory in living lies not in never falling, but in rising every time we fall.
{"@timestamp":"2020-11-08T00:24:00.010+05:30","severity":"INFO","service":"praveenoruganti-springboot-batch-scheduler","trace":"","span":"","parent":"","exportable":"","thread":"scheduling-1","class":"c.praveen.batch.scheduler.BatchScheduler","methodName":"quoteGeneratorScheduler","lineNo":"35","message":"quoteGeneratorScheduler Ends","stacktrace":""}

```

### Drawbacks of SpringBoot Batch Scheduler
We need to create seperate app for batch scheduler as it needs to run in single instance which is a drawback in terms of maintenance which can be eliminated by using [ShedLock](https://github.com/praveenoruganti/praveenoruganti-springboot-shedlock).
