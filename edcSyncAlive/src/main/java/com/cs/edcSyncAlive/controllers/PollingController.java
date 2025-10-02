package com.cs.edcSyncAlive.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.edcSyncAlive.messages.MessageFetcher;
import com.cs.edcSyncAlive.messages.MessageSender;

@RestController
@RequestMapping("/poller")
public class PollingController {

	@Autowired
    private MessageFetcher poller;
	
	@Autowired
	private MessageSender sender;


    @PostMapping("/pause")
    public String pause() {
        poller.pausePolling();
        return "Polling paused";
    }

    @PostMapping("/resume")
    public String resume() {
        poller.resumePolling();
        return "Polling resumed";
    }

    @GetMapping("/status")
    public String status() {
        return "Polling is " + (poller.isPollingEnabled() ? "enabled" : "paused");
    }
    
    @GetMapping("/count")
    public  ResponseEntity<Map<String, String>> count() {
    	 Map<String, String> result = sender.getQueueMessageCount();
        return ResponseEntity.ok(result);
    }

}

