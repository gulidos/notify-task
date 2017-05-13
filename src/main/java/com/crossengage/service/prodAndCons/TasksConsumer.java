package com.crossengage.service.prodAndCons;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.crossengage.application.Settings;
import com.crossengage.domain.User;
import com.crossengage.repo.UserRepo;
import com.crossengage.service.HandlerBuffer;
import com.crossengage.service.Task;

public class TasksConsumer implements Runnable {
	private final HandlerBuffer buffer;
	private ExecutorService executor;
	
	public TasksConsumer(HandlerBuffer buffer, UserRepo repo) {
		super();
		this.buffer = buffer;
		executor = Executors.newFixedThreadPool(Settings.NUMBER_OF_THREADs);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Task task = buffer.get();
				CompletableFuture.runAsync(() -> task.process("Welcome"), executor);			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
