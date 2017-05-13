package com.crossengage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossengage.domain.User;
import com.crossengage.domain.User.Contact;

public class ContactTest {
	private static final String message = "Welcome";
	private ByteArrayOutputStream out;
	
	@Before
	public void setUpStreams() {
		out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Test 
	public void sendEmailTest() {
		User user = new User(1, true, Contact.email, "test1@mail.com", "+999999999999");
		user.doMessage(message);
		Assert.assertEquals("Email send to test1@mail.com with text: Welcome", out.toString().trim());
	}
	
	@Test 
	public void sendSmsTest() {
		User user = new User(1, true, Contact.phone, "test1@mail.com", "+999999999999");
		user.doMessage(message);
		Assert.assertEquals("Sms send to +999999999999 with text: Welcome", out.toString().trim());
	}
	
	@Test 
	public void sendSmsAndEmailTest() {
		User user = new User(1, true, Contact.all, "test1@mail.com", "+999999999999");
		user.doMessage(message);
		String outText = "Sms send to +999999999999 with text: Welcome" 
					+ "\n"
					+ "Email send to test1@mail.com with text: Welcome";
		Assert.assertEquals(outText, out.toString().trim());
	}
	
	@Test 
	public void sendNothing() {
		User user = new User(1, true, Contact.none, "test1@mail.com", "+999999999999");
		user.doMessage(message);
		Assert.assertEquals("", out.toString().trim());
	}
	
}
