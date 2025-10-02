package com.cs.edcSyncAlive.messages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.cs.edcSyncAlive.Utils.ValidateUtils;
import com.cs.edcSyncAlive.configurations.QueueProperties;
import com.cs.edcSyncAlive.controllers.GsonListDeserializer;
import com.cs.edcSyncAlive.dao.ITerminalDao;
import com.cs.edcSyncAlive.dto.RequestParams;
import com.cs.edcSyncAlive.exceptions.AppException;
import com.cs.edcSyncAlive.services.EdcSyncAliveValidate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class MessageFetcher {
	private Logger logger = LoggerFactory.getLogger(MessageFetcher.class);

	@Autowired
	private AmazonSQS amazonSQS;
	
	@Autowired
	private ITerminalDao terminalDao;
	
	@Autowired
	private QueueProperties queue;
	
	@Autowired
	EdcSyncAliveValidate validate;

	private volatile boolean pollingEnabled = false;

	@Scheduled(fixedDelayString = "${aws.queue.reciever.delay}")
	public void pollSqs() {

		if (!pollingEnabled) {
			logger.info("--Stop pulling--");
			return; // หยุดทำงาน
		}
		Gson gson = new Gson();
		try {
			String queueUrl = amazonSQS.getQueueUrl(queue.getName()).getQueueUrl();
			ReceiveMessageRequest req = new ReceiveMessageRequest(queueUrl)
					.withMaxNumberOfMessages(Integer.parseInt(queue.getReciever().getMaximum()))
					.withWaitTimeSeconds(1);
			List<Message> messages = amazonSQS.receiveMessage(req).getMessages();

			// ----------------------------------
			RequestParams requestParams = null;
			List<RequestParams> list = new ArrayList<>();
			List<String> error = new ArrayList<String>();
			Map<String, RequestParams> map = new HashMap<String, RequestParams>();
			// ----------------------------------
			
			for (Message message : messages) {

				if (message.getBody() instanceof String) {
//					logger.info("payload is string");
					try {
						gson = new GsonBuilder().registerTypeAdapter(List.class, new GsonListDeserializer()) // ✅ ใช้
																												// Custom
																												// Deserializer
								.create();
						requestParams = gson.fromJson((String) message.getBody(), RequestParams.class);
						logger.info("Received: " + requestParams.toString());
						
						if(validateData(requestParams)) {
							map.put(requestParams.getTerminalId(), requestParams);
							list.add(requestParams);
						}
					} catch (Exception e) {
						error.add(message.getBody());
						logger.error("ERROR :" + e.getMessage());
					}
				}
				// ลบ message ออก
				amazonSQS.deleteMessage(queueUrl, message.getReceiptHandle());
			}

			logger.info("All size     :" + messages.size());
			logger.info("Success size :" + list.size());
			logger.info("Error size   :" + error.size());
			logger.info("--------------------------");
			if (list.size() > 0) {
				terminalDao.updateLastAlive(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SQS not available yet: " + e.getMessage());
		}
	}

	public boolean isQueueAvailable(AmazonSQS sqsClient, String queueName) {
		try {
			String queueUrl = sqsClient.getQueueUrl(new GetQueueUrlRequest(queueName)).getQueueUrl();
			logger.info("Queue URL: " + queueUrl);
			return true;
		} catch (QueueDoesNotExistException e) {
			logger.error("Queue does not exist: " + queueName);
			return false;
		} catch (Exception e) {
			logger.error("Failed to connect to SQS: " + e.getMessage());
			return false;
		}
	}

	public boolean testSendMessage(AmazonSQS sqsClient, String queueUrl) {
		try {
			sqsClient.sendMessage(new SendMessageRequest(queueUrl, "Test message"));
			logger.info("Send message successful");
			return true;
		} catch (Exception e) {
			logger.error("Failed to send message: " + e.getMessage());
			return false;
		}
	}

	public boolean isSqsReady() {
		if (!isQueueAvailable(amazonSQS, queue.getName())) {
			return false;
		}
//	    String queueUrl = amazonSQS.getQueueUrl(new GetQueueUrlRequest(queueName)).getQueueUrl();
//	    return testSendMessage(amazonSQS, queueUrl);
		return true;
	}

	public void pausePolling() {
		pollingEnabled = false;
	}

	public void resumePolling() {
		pollingEnabled = true;
	}

	public boolean isPollingEnabled() {
		return pollingEnabled;
	}
	
	private boolean validateData(RequestParams req) throws Exception {
		
		try {
			ValidateUtils.isValidTransactionId(req.getTransactionId());
			ValidateUtils.isValidTerminalId(req.getTerminalId());
			ValidateUtils.isValidMerchantId(req.getMerchantId());
			
			int validTerminal = validate.validTerminalDB(req.getTerminalId());
			int validMerchant = validate.validMerchantDB(req.getMerchantId());
			
			if(validTerminal<=0) {
				throw new Exception("["+req.getTerminalId()+"]Not found terminalId or merchantId");
			}else {
				req.setTerminalIdPk(validTerminal);
			}
			if(validMerchant<=0) {
				throw new Exception("["+req.getMerchantId()+"]Not found terminalId or merchantId");
			}else {
				req.setMerchantIdPk(validMerchant);
			}
			
			/**** Check the association between merchantId and terminalId. ****/
			validate.checkMerchantTerminalAssociation(validTerminal,validMerchant);
			
		}catch(AppException v) {
			logger.error( v.getCode()+" ,"+v.getMessage());
			throw v;
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		
		return true;
	}
}
