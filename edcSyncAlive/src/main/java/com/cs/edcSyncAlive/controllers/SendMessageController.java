package com.cs.edcSyncAlive.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.edcSyncAlive.messages.MessageSenderScheduler;
import com.cs.edcSyncAlive.services.SendMessageService;

@RestController
@RequestMapping("/send")
public class SendMessageController {
	
	private Logger logger = LoggerFactory.getLogger(SendMessageController.class);
	
	@Autowired
	private SendMessageService sendService;
	
	@Autowired
	private MessageSenderScheduler msg;

//	@PostMapping("/a80")
//	public String a80_send() {
//		logger.info("****[a80_send]****");
//		try {
//			sendService.sendMessageToSqsFromA80();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Send message to sqs failed."+e.getMessage();
//		}
//		return "Send message to sqs successfully.";
//	}
//
//	@PostMapping("/s80")
//	public String s80_send() {
//		logger.info("****[s80_send]****");
//		try {
//			sendService.sendMessageToSqsFromS80();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Send message to sqs failed."+e.getMessage();
//		}
//		return "Send message to sqs successfully.";
//	}
	
	@PostMapping("/a80/pause")
	public String a80_pause() {
		msg.pauseSendingA80();
		return "a80 pause";
	}
	@PostMapping("/a80/resume")
	public String a80_resume() {
		msg.resumeSendingA80();
		return "a80 resume";
	}
	@GetMapping("/a80/status")
	public String a80_status() {
		return "a80 is " + (msg.isSendingEnabledA80() ? "enabled" : "paused");
	}
	@PostMapping("/s80/pause")
	public String s80_pause() {
		msg.pauseSendingS80();
		return "s80 pause";
	}
	@PostMapping("/s80/resume")
	public String s80_resume() {
		msg.resumeSendingS80();
		return "s80 resume";
	}
	@GetMapping("/s80/status")
	public String s80_status() {
		return "s80 is " + (msg.isSendingEnabledS80() ? "enabled" : "paused");
	}
	
}
