package com.cs.edcSyncAlive.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.cs.edcSyncAlive.constants.Constants;

@Configuration
public class AwsSQSConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(AwsSQSConfigTest.class);
	
//	@Value("${spring.cloud.aws.credentials.access-key}")
//	private String accessKey;
//	@Value("${spring.cloud.aws.credentials.secret-key}")
//	private String secretKey;
//	@Value("${spring.cloud.aws.region.static}")
//	private String region;
	
	@Autowired
	private AwsProperties awsProperties;

	@Bean
	public AmazonSQS amazonSQS() {
		// กรณีใช้ LocalStack หรือ endpoint อื่นๆ
		try {
			return AmazonSQSClientBuilder.standard()
					.withEndpointConfiguration(
							new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", awsProperties.getRegion().getStatic()))
					.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsProperties.getCredentials().getAccessKey(), awsProperties.getCredentials().getSecretKey())))
					.build();
		} catch (Exception e) {
			// log error แต่ไม่โยน exception
			logger.warn("Cannot create AmazonSQS client, will retry later: " + e.getMessage());
			return null; // หรือ สร้าง proxy แบบ dummy ที่ไม่ทำอะไร
		}
	}
}
