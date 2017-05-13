package com.crossengage.service;

import com.crossengage.domain.User;

public class SmsTask implements Task {
	private User user;
	public SmsTask(User user) {
		super();
		this.user = user;
	}


	@Override
	public void process(String message) {
		System.out.println("Sms send to " + user.getPhone() + " with text: " + message);
	}

}
