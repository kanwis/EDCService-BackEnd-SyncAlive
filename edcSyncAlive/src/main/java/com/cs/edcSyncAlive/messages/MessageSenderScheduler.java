package com.cs.edcSyncAlive.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cs.edcSyncAlive.services.SendMessageService;

@Service
public class MessageSenderScheduler {
	
	private Logger logger = LoggerFactory.getLogger(MessageSenderScheduler.class);
	
	
	@Autowired
	private SendMessageService sendService;
	
	private volatile boolean sendingEnabledA80 = false;
	private volatile boolean sendingEnabledS80 = false;

	@Scheduled(fixedDelayString = "${aws.queue.sender.delaya80}")
	public void a80_send() {
		if (!sendingEnabledA80) {
			logger.info("--Stop sending a80--");
			return; // หยุดทำงาน
		}
		logger.info("****[a80_send]****");
		try {
			sendService.sendMessageToSqsFromA80();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	@Scheduled(fixedDelayString = "${aws.queue.sender.delays80}")
	public void s80_send() {
		if (!sendingEnabledS80) {
			logger.info("--Stop sending s80--");
			return; // หยุดทำงาน
		}
		logger.info("****[s80_send]****");
		try {
			sendService.sendMessageToSqsFromS80();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	public void pauseSendingA80() {
		sendingEnabledA80 = false;
	}

	public void resumeSendingA80() {
		sendingEnabledA80 = true;
	}

	public boolean isSendingEnabledA80() {
		return sendingEnabledA80;
	}
	
	public void pauseSendingS80() {
		sendingEnabledS80 = false;
	}
	
	public void resumeSendingS80() {
		sendingEnabledS80 = true;
	}
	
	public boolean isSendingEnabledS80() {
		return sendingEnabledS80;
	}

}
