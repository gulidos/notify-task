package com.crossengage.repo;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.crossengage.repo.UserRepo;
import com.crossengage.repo.UserRepoImpl;

public class UserRepositoryTest {
	private UserRepo repo;

	@Before
	public void loadFile() {
		File repoFile = new File(this.getClass().getResource("/test_user_data.txt").getFile());
		repo = new UserRepoImpl(repoFile.toPath(), ",");
	}
	
	@Test
	public void fromEmptyStream() throws IOException {
		String [] users = new String[] {"id,active,contactBy,email,phone"};
		Stream<String> stream = Arrays.stream(users);
		assertEquals(0, repo.getStreamOfValidUsers(stream).count());
	}
	
	@Test
	public void fromInvalidInput() throws IOException {
		String [] users = new String[] {
				"id,active,contactBy,email,phone",
				"1, true, wrong, wrong, wrong"
		};
		Stream<String> stream = Arrays.stream(users);
		assertEquals(0, repo.getStreamOfValidUsers(stream).count());
	}
	
	@Test
	public void fromInvalidUserId() throws IOException {
		String [] users = new String[] {
				"id,active,contactBy,email,phone",
				"1n, true, phone, test1@mail.com, +7333222332"
		};
		Stream<String> stream = Arrays.stream(users);
		assertEquals(0, repo.getStreamOfValidUsers(stream).count());
	}
}
