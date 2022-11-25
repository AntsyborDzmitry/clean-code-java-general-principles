package com.epam.engx.cleancode.errorhandling.task1;

public class InvalidDaoException extends RuntimeException{
    final String message;
    public InvalidDaoException(String s) {
        super();
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
