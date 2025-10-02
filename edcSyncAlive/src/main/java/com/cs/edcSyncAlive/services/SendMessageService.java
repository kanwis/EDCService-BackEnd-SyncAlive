package com.cs.edcSyncAlive.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.edcSyncAlive.dao.ITmpInsertAliveA80Dao;
import com.cs.edcSyncAlive.dao.ITmpInsertAliveS80Dao;
import com.cs.edcSyncAlive.entities.TmpInsertAliveA80;
import com.cs.edcSyncAlive.entities.TmpInsertAliveS80;
import com.cs.edcSyncAlive.messages.MessageSender;

@Service
public class SendMessageService {
	
	private Logger logger = LoggerFactory.getLogger(SendMessageService.class);
	
	@Autowired
	private ITmpInsertAliveA80Dao a80Dao;
	
	@Autowired
	private ITmpInsertAliveS80Dao s80Dao;
	
	@Autowired
	private MessageSender sender;
	
	public void sendMessageToSqsFromA80 () throws Exception {
		List<TmpInsertAliveA80> list = a80Dao.findAll();
		logger.info("[a80]Size : "+list.size());
		int count = 0;int failed =0 ;
		if(list !=null && list.size()>0) {
			for(TmpInsertAliveA80 obj : list) {
				if(obj.getJson() !=null && obj.getJson().length()>0) {
					try {
						String resp = sender.sendMessageToQueue(obj.getJson());
						if (resp == null || resp.isBlank() || resp.isEmpty()) {
							failed++;
							logger.info("[a80]Send failed, resp=null");
							continue;
						}
						logger.info("[a80]Send to sqs successfully.");
						try {
							a80Dao.deleteById(obj.getId());
							logger.info("[a80]ID = "+obj.getId()+" deleted successfully.");
						} catch (Exception e) {
							logger.error("[a80]ERROR : Delete tmp ,"+e.getMessage());
							failed++;
						}
						count++;
					}catch(Exception e) {
						logger.error("[a80]ERROR : Send message a80 ,"+e.getMessage());
						failed++;
					}
					
				}
			}
			logger.info("[a80]Success : "+count);
			logger.info("[a80]Failed : "+failed);
		}
		logger.info("----------**[end a80]**----------");
	}
	
	public void sendMessageToSqsFromS80 () throws Exception {
		List<TmpInsertAliveS80> list = s80Dao.findAll();
		logger.info("[s80]Size : "+list.size());
		int count = 0;int failed =0 ;
		if(list !=null && list.size()>0) {
			for(TmpInsertAliveS80 obj : list) {
				if(obj.getJson() !=null && obj.getJson().length()>0) {
					try {
						String resp = sender.sendMessageToQueue(obj.getJson());
						if (resp == null || resp.isBlank() || resp.isEmpty()) {
							failed++;
							logger.info("[s80]Send failed, resp=null");
							continue;
						}
						logger.info("[s80]Send to sqs successfully.");
						try {
							s80Dao.deleteById(obj.getId());
							logger.info("[s80]ID = "+obj.getId()+" deleted successfully.");
						} catch (Exception e) {
							logger.error("[s80]ERROR : Delete tmp ,"+e.getMessage());
							failed++;
						}
						count++;
					}catch(Exception e) {
						logger.error("[s80]ERROR : Send message a80 ,"+e.getMessage());
						failed++;
					}
					
				}
			}
			logger.info("[s80]Success : "+count);
			logger.info("[s80]Failed : "+failed);
		}
		logger.info("----------**[end s80]**----------");
	}

}
