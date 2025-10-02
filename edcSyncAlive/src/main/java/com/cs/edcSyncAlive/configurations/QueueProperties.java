package com.cs.edcSyncAlive.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws.queue")
public class QueueProperties {
	
	private String name;
	private Reciever reciever = new Reciever();
	private Sender sender = new Sender();
	
	public static class Reciever{
		private String delay;
		private String maximum;
		public String getDelay() {
			return delay;
		}
		public void setDelay(String delay) {
			this.delay = delay;
		}
		public String getMaximum() {
			return maximum;
		}
		public void setMaximum(String maximum) {
			this.maximum = maximum;
		}
	}
	
	public static class Sender {
		private String delaya80;
		private String delays80;
		public String getDelaya80() {
			return delaya80;
		}
		public void setDelaya80(String delaya80) {
			this.delaya80 = delaya80;
		}
		public String getDelays80() {
			return delays80;
		}
		public void setDelays80(String delays80) {
			this.delays80 = delays80;
		}
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Reciever getReciever() {
		return reciever;
	}
	public Sender getSender() {
		return sender;
	}
	

}
