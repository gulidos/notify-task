package com.crossengage.application;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.crossengage.domain.User;
import com.crossengage.repo.UserRepo;
import com.crossengage.repo.UserRepoImpl;

public class AppManager {
	UserRepo userRepo;
	Path dataFile;
	
	public AppManager(Path dataFile) {	
		this.dataFile = dataFile;
		userRepo = new UserRepoImpl(dataFile, Settings.delimeter);
	}

	public void start() throws IOException {
		Stream<User> usersStream = userRepo.getStreamOfValidUsers();
	}
}
