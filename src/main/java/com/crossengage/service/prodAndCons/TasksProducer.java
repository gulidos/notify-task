package com.crossengage.service.prodAndCons;

import java.io.IOException;
import java.util.Arrays;

import com.crossengage.domain.User;
import com.crossengage.repo.UserRepo;
import com.crossengage.service.EmailTask;
import com.crossengage.service.HandlerBuffer;
import com.crossengage.service.SmsTask;
import com.crossengage.service.Task;

public class TasksProducer implements Runnable {
	private final HandlerBuffer buffer;
	private final UserRepo repo;
	

	public TasksProducer(HandlerBuffer buffer, UserRepo repo) {	
		this.buffer = buffer;
		this.repo = repo;
	}
	

	@Override
	public void run() {
		try {
			repo.getStreamOfValidUsers()
			.map(user -> getTasksForUser(user))
			.flatMap(tasks -> Arrays.stream(tasks))
			.forEach(task -> buffer.put(task));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public Task[] getTasksForUser(User user) {
		switch (user.getContactBy()) {
		case email: return new Task[] { new EmailTask(user) };
		case phone: return new Task[] { new SmsTask(user)   };
		case all: return new Task[] { new EmailTask(user), new SmsTask(user)   };
		case none: return new Task[] {  };
		default:   return new Task[] {  };
		}
	}
}
