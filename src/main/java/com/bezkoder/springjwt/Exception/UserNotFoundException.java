package com.bezkoder.springjwt.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int userId){
        super("Could not found the user with id "+ userId);
    }
}
