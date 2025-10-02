package com.cs.edcSyncAlive.services;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.edcSyncAlive.Utils.ValidateUtils;
import com.cs.edcSyncAlive.constants.Constants;
import com.cs.edcSyncAlive.dao.ITmpInsertAliveA80Dao;
import com.cs.edcSyncAlive.dto.RequestParams;
import com.cs.edcSyncAlive.entities.TmpInsertAliveA80;
import com.cs.edcSyncAlive.exceptions.AppException;
import com.cs.edcSyncAlive.messages.MessageFetcher;
import com.cs.edcSyncAlive.messages.MessageSender;
import com.google.gson.Gson;



@Service
public class EdcSyncAliveA80Process {

	private static final Logger loggerA80 = LoggerFactory.getLogger(Constants.A80);
	
	@Autowired
	private ITmpInsertAliveA80Dao a80Dao;
	
	@Autowired
	private MessageSender sender;
	
	@Autowired
	private MessageFetcher sqs;
	
	
	public void tmpAliveA80(RequestParams req) throws Exception {
		
//		try {
//			validateInsert(req);
//		}catch(AppException v) {
//			throw new AppException(ResponseCode.INVALID.formatMessage(v.getMessage()),ResponseCode.INVALID.getCode());
//		}catch(Exception e) {
//			throw e;
//		}
		
		saveTmpAndSendToSqsA80(req);
	}
	
	private void saveTmpAndSendToSqsA80(RequestParams req) throws Exception {
		try {
			Date createdDate = new Date();
			Gson g = new Gson();
			req.setModel(Constants.A80_MODEL);
			req.setCreatedDate(createdDate);
			String json = g.toJson(req);
			
			TmpInsertAliveA80 tmp = new TmpInsertAliveA80();
			tmp.setJson(json);
			tmp.setCreatedDate(createdDate);
			
			try {
				
				String resp = null;
				if(sqs.isSqsReady()) {
					loggerA80.warn("-->> Ready...");
					resp = sender.sendMessageToQueue(req);
				}else {
					loggerA80.warn("-->> Not Ready...");
					throw new Exception("Unable send to SQS.");
				}
				if (resp == null) {
					throw new Exception("Unable send to SQS.");
				}else {
					loggerA80.info("Send to sqs successfully.");
//					try {
//						a80Dao.deleteById(result.getId());
//						loggerA80.info("ID = "+result.getId()+" deleted successfully.");
//					} catch (Exception e) {
////						e.printStackTrace();
//						loggerA80.error(e.getMessage());
//					}
				}
				
			} catch (Exception e) {
//				e.printStackTrace();
//				loggerA80.error("producer sendMessage :" + e.getMessage());
				TmpInsertAliveA80 result = a80Dao.save(tmp);
				loggerA80.info("Data saved successfully ! ---" + result.getId());
				throw e;
			}

		} catch (Exception e) {
//			e.printStackTrace();
//			loggerA80.error("saveTmpInsertA80 :" + e.getMessage());
			throw e;
		}
		
	}
	
}
