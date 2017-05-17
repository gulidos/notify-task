package com.crossengage.domain;

import java.io.IOException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;



public class UserTest {
	
	@Test
	public void parseGoodString() throws IOException {
		String str = "1 ,true ,email , test1@mail.com ,+999999999999";
		User user = User.parseAsOptional(str.split(",")).get();
		Assert.assertEquals(user.getId(), 1);
		Assert.assertTrue(user.isActive());
		Assert.assertEquals(user.getEmail(), "test1@mail.com");
		Assert.assertEquals(user.getPhone(), "+999999999999");
	}
	
	@Test 
	public void parseBadStringLength() {
		String str = "1 ,true ,phone  test1@mail.com ,+999999999999";
		Optional<User> optUser = User.parseAsOptional(str.split(","));
		Assert.assertFalse(optUser.isPresent());
	}
	
	@Test 
	public void parseBadContactBy() {
		String str = "1 ,true ,emails , test1@mail.com ,+999999999999";
		Optional<User> optUser = User.parseAsOptional(str.split(","));
		Assert.assertFalse(optUser.isPresent());	
	}
}
