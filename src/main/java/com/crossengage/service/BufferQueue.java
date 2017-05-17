package com.crossengage.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.crossengage.application.Settings;

public class BufferQueue implements Buffer {
	private final BlockingQueue<Task> queue;

	public BufferQueue() {
		queue = new LinkedBlockingDeque<>(Settings.QUEUE_SIZE);
	}

	@Override
	public void put(Task task)  {
		try {
			queue.put(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Task get() throws InterruptedException {
		return queue.take();
	}

}
