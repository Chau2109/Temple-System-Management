package com.bezkoder.springjwt.Exception;

public class ChuaNotFoundException extends RuntimeException{
    public ChuaNotFoundException(int chuaId) {
        super("Could not found Chua with id "+ chuaId);
    }
}
