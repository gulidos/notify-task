package com.crossengage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.crossengage.repo.UserRepo;
import com.crossengage.repo.UserRepoImpl;

public class App {
	private final static int THREAD_NUMBER = 100;
	private final static String delimeter = ",";
	
	
    public static void main(String[] args) {
		if (!isArgumentsOk(args)) return;

		Path data = Paths.get(args[0]);
		if (!Files.exists(data, LinkOption.NOFOLLOW_LINKS)) {
			System.err.println("File not exists");
			return;
		}
		
    	UserRepo userRepo = new UserRepoImpl(data, delimeter);
		String message = args[1];
        try {
			sendMessagesInParallel(userRepo, message);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
    }

    
	private static boolean isArgumentsOk(String[] args) {
		if (args[0] == null || args[0].isEmpty()) {
			System.err.println("Filename is necessary");
			return false;
		}
		
		if (args[1] ==null || args[1].isEmpty()) {
			System.err.println("Please enter a message");
			return false;
		}
		return true;
	}

	
	static void sendMessagesSequentialy(UserRepo userRepo, String message) throws IOException {
        userRepo.getStreamOfValidUsers()
        	.forEach(user -> user.doMessage(message));
	}
	

	static void sendMessagesInParallel(UserRepo userRepo, final String message) throws IOException {
		ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);
		List<CompletableFuture<Void>> resultsFutures = userRepo.getStreamOfValidUsers()
			.map(user -> CompletableFuture.runAsync(() -> user.doMessage(message), executor))
			.collect(Collectors.toList());
		
		resultsFutures.stream()
			.map(CompletableFuture::join)
			.count();
		executor.shutdownNow();
	}
 
}
