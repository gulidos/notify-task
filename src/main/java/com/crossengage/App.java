package com.crossengage;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.crossengage.application.AppManager;

public class App {
    public static void main(String[] args) {
		if (!isArgumentsOk(args)) return;

		Path dataFile = Paths.get(args[0]);
		if (!Files.exists(dataFile, LinkOption.NOFOLLOW_LINKS)) {
			System.err.println("File not exists");
			return;
		}
		
		AppManager app = new AppManager(dataFile);
		app.start();
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

}
