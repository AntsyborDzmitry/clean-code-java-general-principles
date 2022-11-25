package com.epam.engx.cleancode.errorhandling.task1;

public class ReportBuilderException extends RuntimeException{
    final String message;
    public ReportBuilderException(String s) {
        super();
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
