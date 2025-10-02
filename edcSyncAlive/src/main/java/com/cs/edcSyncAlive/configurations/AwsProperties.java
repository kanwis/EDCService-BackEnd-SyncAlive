package com.cs.edcSyncAlive.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.cloud.aws")
public class AwsProperties {

	private Credentials credentials = new Credentials();
	private Region region = new Region();

	public static class Credentials {
		private String accessKey;
		private String secretKey;

		// getters and setters
		public String getAccessKey() {
			return accessKey;
		}

		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}

		public String getSecretKey() {
			return secretKey;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}
	}

	public static class Region {
		private String staticRegion;

		// getter and setter
		public String getStatic() {
			return staticRegion;
		}

		public void setStatic(String staticRegion) {
			this.staticRegion = staticRegion;
		}
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public Region getRegion() {
		return region;
	}

	
	
	

}
