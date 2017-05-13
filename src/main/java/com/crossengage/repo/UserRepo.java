package com.crossengage.repo;

import java.io.IOException;
import java.util.stream.Stream;

import com.crossengage.domain.User;

public interface UserRepo {

	Stream<User> getStreamOfValidUsers(Stream<String> stream) throws IOException;

	Stream<User> getStreamOfValidUsers() throws IOException;

}