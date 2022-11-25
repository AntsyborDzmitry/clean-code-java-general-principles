package com.epam.engx.cleancode.errorhandling.task1.models;

public class Report {

    private static final String MESSAGE_FORMAT = "User Total: %s$";

    private final double amount;
    private final String amountType;
    private final String errorMessage;

    public Report(double amount, String amountType, String errorMessage) {
        this.amountType = amountType;
        this.errorMessage = errorMessage;
        this.amount = amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public String getMessage() {
        if (hasErrorMessage()) {
            return errorMessage;
        }
        return String.format(MESSAGE_FORMAT, amount);
    }

    private boolean hasErrorMessage() {
        return errorMessage.length() > 0;
    }
}
