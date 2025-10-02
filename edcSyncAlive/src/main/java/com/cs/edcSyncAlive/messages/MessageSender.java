package com.cs.edcSyncAlive.messages;



import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.cs.edcSyncAlive.configurations.QueueProperties;
import com.cs.edcSyncAlive.dto.RequestParams;
import com.google.gson.Gson;

@Service
public class MessageSender {
	
	private Logger logger = LoggerFactory.getLogger(MessageSender.class);
	
	@Autowired
	private AmazonSQS amazonSQS;
	
	@Autowired
	private QueueProperties queue;
	
	public String sendMessageToQueue(RequestParams req) {
		try {
			Gson g = new Gson();
			String text = g.toJson(req);
			String queueUrl = amazonSQS.getQueueUrl(queue.getName()).getQueueUrl();
	        SendMessageRequest request = new SendMessageRequest()
	                .withQueueUrl(queueUrl)
	                .withMessageBody(text);
	
	        SendMessageResult result = amazonSQS.sendMessage(request);
	        System.out.println("Message sent with ID: " + result.getMessageId());
	        return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed to send message: " + e.getMessage());
            return null;
        }
    }
	
	public String sendMessageToQueue(String text) {
		try {
			String queueUrl = amazonSQS.getQueueUrl(queue.getName()).getQueueUrl();
	        SendMessageRequest request = new SendMessageRequest()
	                .withQueueUrl(queueUrl)
	                .withMessageBody(text);
	
	        SendMessageResult result = amazonSQS.sendMessage(request);
	        System.out.println("Message sent with ID: " + result.getMessageId());
	        return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed to send message: " + e.getMessage());
            return null;
        }
    }
	
	public Map<String, String> getQueueMessageCount() {
		 Map<String, String> attributes = null;
	    try {
	        String queueUrl = amazonSQS.getQueueUrl(queue.getName()).getQueueUrl();
	        GetQueueAttributesRequest request = new GetQueueAttributesRequest()
	                .withQueueUrl(queueUrl)
//	                .withAttributeNames("All");
	        		.withAttributeNames("ApproximateNumberOfMessages", "ApproximateNumberOfMessagesNotVisible");

	        GetQueueAttributesResult result = amazonSQS.getQueueAttributes(request);
//	        String count = result.getAttributes().get("ApproximateNumberOfMessages");
	        attributes = result.getAttributes();

//	        Gson g = new Gson();
//			String text = g.toJson(attributes);
	        return attributes;
	    } catch (Exception e) {
	    	attributes = new HashMap<String, String>();
	    	attributes.put("ERROR", e.getMessage());
	        return attributes;
	    }
	}

}
