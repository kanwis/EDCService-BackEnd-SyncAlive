package com.cs.edcSyncAlive.controllers;



import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.edcSyncAlive.constants.Constants;
import com.cs.edcSyncAlive.constants.ResponseCode;
import com.cs.edcSyncAlive.dto.RequestParams;
import com.cs.edcSyncAlive.exceptions.AppException;
import com.cs.edcSyncAlive.services.EdcSyncAliveA80Process;
import com.cs.edcSyncAlive.services.EdcSyncAliveS80Process;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/api/v1", produces = "application/json")
public class EdcSyncAliveController {

	private static final Logger loggerS80 = LoggerFactory.getLogger(Constants.S80);
	private static final Logger loggerA80 = LoggerFactory.getLogger(Constants.A80);

	@Autowired
	private EdcSyncAliveA80Process a80Process;
	
	@Autowired
	private EdcSyncAliveS80Process s80Process;

	@PostMapping("/tmpAliveA80")
	public ResponseEntity<Map<String, Object>> tmpAliveA80WithAWSSQS(@RequestBody RequestParams req) {
		loggerA80.info("*******[A80 tmpAliveA80WithAWSSQS]*******"+req.toString());
		try {
			a80Process.tmpAliveA80(req);
			return mapResponse(ResponseCode.SUCCESS.getMessage(), ResponseCode.SUCCESS.getCode(), null, req.getTransactionId(), loggerA80);
		}catch (AppException v) {
			return mapResponse(v.getMessage(), v.getCode(), null, req.getTransactionId(), loggerA80);
		} catch (Exception e) {
			loggerA80.error("Exception : " + e.getMessage());
			return mapResponse(ResponseCode.EXCEPTION.formatMessage(e.getMessage()), ResponseCode.EXCEPTION.getCode(), null, req.getTransactionId(), loggerA80);
		}
		
	}
	
	@PostMapping("/tmpAliveS80")
	public ResponseEntity<Map<String, Object>> tmpAliveS80WithAWSSQS(@RequestBody RequestParams req) {
		loggerS80.info("*******[S80 tmpAliveS80WithAWSSQS]*******"+req.toString());
		try {
			s80Process.tmpAliveS80(req);
			return mapResponse(ResponseCode.SUCCESS.getMessage(), ResponseCode.SUCCESS.getCode(), null, req.getTransactionId(), loggerS80);
		}catch (AppException v) {
			return mapResponse(v.getMessage(), v.getCode(), null, req.getTransactionId(), loggerS80);
		} catch (Exception e) {
			loggerS80.error("Exception : " + e.getMessage());
			return mapResponse(ResponseCode.EXCEPTION.formatMessage(e.getMessage()), ResponseCode.EXCEPTION.getCode(), null, req.getTransactionId(), loggerS80);
		}
	}
	
	private ResponseEntity<Map<String, Object>> mapResponse(String resultMessage, Object resultValue,
			Object resultObject, String transactionId, Logger logger) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resultMessage", resultMessage);
		result.put("resultValue", resultValue);
		result.put("transactionId", transactionId);
		result.put("resultObject", resultObject);
		
		//---------------------------------------------
		  // ใช้ Jackson แปลงเป็น JSON String
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        int contentLength = 0;
		try {
			jsonString = objectMapper.writeValueAsString(result);
			 // คำนวณขนาดของ JSON ในไบต์
	        contentLength = jsonString.getBytes(StandardCharsets.UTF_8).length;
//	        logger.info("JSON String: " + jsonString+", Content-Length: " + contentLength + " bytes");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//---------------------------------------------
		HttpHeaders headers = new HttpHeaders();
		headers.setContentLength(contentLength);
		ResponseEntity<Map<String, Object>> entity = new ResponseEntity<>(result, headers, HttpStatus.OK);

		if (result.toString().length() > 1000) {
			logger.info("Response : " + result.toString().substring(0, 1000).concat("..."));
		} else {
			logger.info("Response : " + result.toString());
		}
		logger.info("---------------------------------------------------------------");
		return entity;
	}
}
