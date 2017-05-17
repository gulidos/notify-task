package com.crossengage.service;

public interface Buffer {
	void put(Task task);
	Task get() throws InterruptedException;
}
