package com.example.acutor.actuatorexample.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomeHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		boolean error=false;
		if (error) {
			return Health.down().withDetail("error key", 123).build();
		}
		return Health.up().build();
	}

}
