package com.cs.edcSyncAlive.services;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.edcSyncAlive.constants.Constants;
import com.cs.edcSyncAlive.dao.ITmpInsertAliveS80Dao;
import com.cs.edcSyncAlive.dto.RequestParams;
import com.cs.edcSyncAlive.entities.TmpInsertAliveS80;
import com.cs.edcSyncAlive.messages.MessageFetcher;
import com.cs.edcSyncAlive.messages.MessageSender;
import com.google.gson.Gson;

@Service
public class EdcSyncAliveS80Process {

	private static final Logger loggerS80 = LoggerFactory.getLogger(Constants.S80);


	@Autowired
	EdcSyncAliveValidate validate;
	
	@Autowired
	private ITmpInsertAliveS80Dao s80Dao;
	
	@Autowired
	private MessageSender sender;
	
	@Autowired
	private MessageFetcher sqs;
	

	public void tmpAliveS80(RequestParams req) throws Exception {
		
//		try {
//			validateInsert(req);
//		}catch(AppException v) {
//			throw new AppException(ResponseCode.INVALID.formatMessage(v.getMessage()),ResponseCode.INVALID.getCode());
//		}catch(Exception e) {
//			throw e;
//		}
		saveTmpAndSendToSqsS80(req);
		
	}
	
	private void saveTmpAndSendToSqsS80(RequestParams req) throws Exception {
		try {
			Date createdDate = new Date();
			Gson g = new Gson();
			req.setModel(Constants.S80_PAX_MODEL);
			req.setCreatedDate(createdDate);
			String json = g.toJson(req);
			
			TmpInsertAliveS80 tmp = new TmpInsertAliveS80();
			tmp.setJson(json);
			tmp.setCreatedDate(createdDate);
			
			try {
				
				String resp = null;
				if(sqs.isSqsReady()) {
					loggerS80.warn("-->> Ready...");
					resp = sender.sendMessageToQueue(req);
				}else {
					loggerS80.warn("-->> Not Ready...");
					throw new Exception("Unable send to SQS.");
				}
				if (resp == null || resp.isBlank() || resp.isEmpty()) {
					throw new Exception("Unable send to SQS.");
				}else {
//					loggerS80.info("resp="+resp.toString());
					loggerS80.info("Send to sqs successfully.");
//					try {
//						s80Dao.deleteById(result.getId());
//						loggerS80.info("ID = "+result.getId()+" deleted successfully.");
//					} catch (Exception e) {
////						e.printStackTrace();
//						loggerS80.error(e.getMessage());
//					}
				}
				
			} catch (Exception e) {
//				e.printStackTrace();
//				loggerS80.error("producer sendMessage :" + e.getMessage());
				TmpInsertAliveS80 result = s80Dao.save(tmp);
				loggerS80.info("Data saved successfully ! ---" + result.getId());
				throw e;
			}

		} catch (Exception e) {
//			e.printStackTrace();
//			loggerS80.error("saveTmpInsertA80 :" + e.getMessage());
			throw e;
		}
		
	}
	
	

}
