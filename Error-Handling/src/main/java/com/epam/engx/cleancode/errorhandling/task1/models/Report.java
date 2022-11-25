package com.epam.engx.cleancode.errorhandling.task1.models;

public class Report {

    private String amountType = "";
    private String errorMessage = "";
    private double amount;

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAmountType(){
        return amountType;
    }

    public String getMessage() {
        if(hasErrorMessage()) {
            return errorMessage;
        }
        return "User Total: " + amount + "$";
    }

    private boolean hasErrorMessage() {
        return errorMessage.length() > 0;
    }
}