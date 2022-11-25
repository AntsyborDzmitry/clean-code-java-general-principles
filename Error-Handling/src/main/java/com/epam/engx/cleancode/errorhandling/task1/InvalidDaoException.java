package com.epam.engx.cleancode.errorhandling.task1;

public class InvalidDaoException extends RuntimeException {
    public InvalidDaoException(String message) {
        super(message);
    }
}
