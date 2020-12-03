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
