package com.crossengage.application;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.crossengage.repo.UserRepo;
import com.crossengage.repo.UserRepoImpl;
import com.crossengage.service.Buffer;
import com.crossengage.service.prodAndCons.TasksConsumer;
import com.crossengage.service.prodAndCons.TasksProducer;

public class AppManager {
	private UserRepo userRepo;
	
	private TasksProducer taskProducer;
	private Buffer buffer;
	private ExecutorService executor;
	
	public AppManager(Path dataFile) {	
		userRepo = new UserRepoImpl(dataFile, Settings.delimeter);
		executor = Executors.newFixedThreadPool(Settings.NUMBER_OF_THREADS);
		taskProducer = new TasksProducer(buffer, userRepo);
	}

	public void start()  {
		for (int i = 0; i < Settings.NUMBER_OF_THREADS; i++) {
			TasksConsumer taskConsumer = new TasksConsumer(buffer, userRepo);
			executor.submit(taskConsumer);
		}
		
		taskProducer.run();
		
	}
}
