package com.cs.edcSyncAlive.configurations;


import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.cs.edcSyncAlive.controllers.GsonListDeserializer;
import com.cs.edcSyncAlive.dto.RequestParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementResultCallback;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

//@Configuration
public class AwsSQSConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(AwsSQSConfig.class);

	@Value("${spring.cloud.aws.credentials.access-key}")
	private String accessKey;
	@Value("${spring.cloud.aws.credentials.secret-key}")
	private String secretKey;
	@Value("${spring.cloud.aws.region.static}")
	private String region;

	@Bean
	SqsAsyncClient sqsAsyncClient() {
		return SqsAsyncClient.builder()
				.endpointOverride(URI.create("http://localhost:4566"))// for test local
				.region(Region.of(region))
				.credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
				.build();
	}

	/*
	 * 1. ON_SUCCESS - Acknowledges a message after successful processing.
	 * 2.ALLWAYS-Acknowledges a message after processing, regardless of success or
	 * error. 3.MANUAL - The framework does not acknowledge messages automatically,
	 * and Acknowledgement objects can be received in the listener method. In this
	 * mode, you must send acknowledgments manually
	 */

	@Bean
	SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory(SqsAsyncClient sqsAsyncClient) {
		return SqsMessageListenerContainerFactory.builder()
				.configure(options -> options.acknowledgementMode(AcknowledgementMode.ALWAYS)
						.acknowledgementInterval(Duration.ofSeconds(3)) // NOTE: With acknowledgementInterval 3 seconds,
						.acknowledgementThreshold(0))
				.acknowledgementResultCallback(new AckResultCallback()).sqsAsyncClient(sqsAsyncClient).build();
	}

	static class AckResultCallback implements AcknowledgementResultCallback<Object> {

		@Override
		public void onSuccess(Collection<Message<Object>> messages) {
//			messages.forEach(System.out::println);
			RequestParams req = null;
			if (messages instanceof List) {
				Message<Object> m = ((List<Message<Object>>) messages).get(0);
				if (m.getPayload() instanceof String) {
					System.out.println("payload is string");
					Gson gson = new GsonBuilder().registerTypeAdapter(List.class, new GsonListDeserializer()).create();
					req = gson.fromJson((String) m.getPayload(), RequestParams.class);
				}
			}
			LOGGER.info("Ack with success {} : at {}", req.getTransactionId(), OffsetDateTime.now());
		}

		@Override
		public void onFailure(Collection<Message<Object>> messages, Throwable t) {
			messages.forEach(System.out::println);
			LOGGER.error("Ack with fail", t);
		}
	}
}
