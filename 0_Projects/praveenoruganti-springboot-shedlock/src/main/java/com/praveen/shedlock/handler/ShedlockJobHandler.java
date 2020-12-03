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
