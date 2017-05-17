package com.crossengage.repo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

import com.crossengage.domain.User;

public class UserRepoImpl implements UserRepo {
	 
    private final Path data;
    private final String delimeter;

    public UserRepoImpl(Path data, String delimeter) {
        this.data = Objects.requireNonNull(data, "File can not be null");
        this.delimeter = Objects.requireNonNull(delimeter, "Delimeter can not be null");
    }

    
    private Stream<String> getStreamStringFromFile() throws IOException {
        return Files.lines(data);
    }
    
   
    @Override
	public Stream<User> getStreamOfValidUsers(Stream<String> stream) throws IOException {
         return stream
                .skip(1)
                .map(line -> line.split(delimeter))
                .map(array -> User.parseAsOptional(array))
                .filter(optU -> optU.isPresent())
                .map(optU -> optU.get())
                .filter(user -> user.isActive());
    }
    
   
    @Override
	public Stream<User> getStreamOfValidUsers() throws IOException {
    	return getStreamOfValidUsers(getStreamStringFromFile());
    }
    
   
               
}
