package com.crossengage.domain;

import java.util.Arrays;

public class User {
	private final long id;
	private final boolean active;
	private final Contact contactBy;
	private final String email;
	private final String phone;
	
	public final static int NUMBER_OF_FIELDS = 5;
	
	public User(long id, boolean active, Contact contactBy, String email, String phone) {
		super();
		this.id = id;
		this.active = active;
		this.contactBy = contactBy;
		this.email = email;
		this.phone = phone;
	}
	

	public static User parse(String[] line) {
		if (line == null || line.length != NUMBER_OF_FIELDS)
			return null;
		try {
			long id = Long.parseLong(line[0].trim());
			boolean active = Boolean.parseBoolean(line[1].trim());  
			Contact contactBy = Contact.valueOf(line[2].trim());
			String email = verifyEmail(line[3]);
			String phone = verifyPhone(line[4]);
			return new User(id, active, contactBy, email, phone);
		} catch (Exception e) {
			System.err.println(e.getMessage() + " in line " + Arrays.asList(line));
			return null;
		}
	}
	
		
	public long getId() {return id;}

	public boolean isActive() {return active;}

	public Contact getContactBy() {return contactBy;}

	public String getEmail() {return email;}

	public String getPhone() {return phone;}
		
	

	@Override
	public String toString() {
		return "User [id=" + id + ", active=" + active  + ", contactBy=" + contactBy + ", email="
				+ email + ", phone=" + phone + "]";
	}

	private static String verifyEmail(String str) {
		//TODO here I have to verify e-mail address. If it isn't correct throw IllegalArgumentException
		return str.trim();
	}
	

	private static String verifyPhone(String str) {
		//TODO here I have to verify phone number. If it isn't correct throw IllegalArgumentException
		return str.trim();
	}

	public enum Contact {
		phone, email, none, all
	}	
		
}
