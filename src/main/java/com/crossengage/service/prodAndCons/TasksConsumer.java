package com.crossengage.service.prodAndCons;

import com.crossengage.application.Settings;
import com.crossengage.repo.UserRepo;
import com.crossengage.service.Buffer;
import com.crossengage.service.Task;

public class TasksConsumer implements Runnable {
	private final Buffer buffer;
	
	
	public TasksConsumer(Buffer buffer, UserRepo repo) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Task task = buffer.get();
				processTask(task);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void processTask(Task task) {
		task.process(Settings.message);
	}
	
}
