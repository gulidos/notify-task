package com.crossengage.service;

public interface HandlerBuffer {
	void put(Task task);
	Task get();
}
