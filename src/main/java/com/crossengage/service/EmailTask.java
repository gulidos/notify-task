package com.crossengage.service;

import com.crossengage.domain.User;

public class EmailTask implements Task {
	private User user;
	public EmailTask(User user) {
		super();
		this.user = user;
	}

	@Override
	public void process(String message) {
		System.out.println("Email send to " + user.getEmail() + " with text: " + message);
	}

}
