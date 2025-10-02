package com.cs.edcSyncAlive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextException;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EdcSyncAliveApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(EdcSyncAliveApplication.class, args);
		} catch (ApplicationContextException e) {
			System.err.println("❌ Spring context failed to start: " + e.getMessage());
			e.printStackTrace(); // หรือ log ลง log file
		} catch (Exception e) {
			System.err.println("❌ Other exception during startup: " + e.getMessage());
		}
	}

}
