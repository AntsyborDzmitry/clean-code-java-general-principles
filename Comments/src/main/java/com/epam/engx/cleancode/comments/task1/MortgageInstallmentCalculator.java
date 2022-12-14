package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    private static final int PERCENT_RATIO_CONVERTER = 100;
    private static final int MONTHS_IN_YEAR = 12;

    private MortgageInstallmentCalculator() {
    }

    public static double calculateMonthlyPayment(
            int principalAmount, int mortgageDurationInYears, double rate) {

        validateInputParameters(principalAmount, mortgageDurationInYears, rate);

        double decimalRate = convertPercentToDecimal(rate);
        int mortgageDurationInMonth = convertYearsToMonth(mortgageDurationInYears);

        return calculatePayment(principalAmount, decimalRate, mortgageDurationInMonth);
    }

    private static void validateInputParameters(int principalAmount, int mortgageDurationInYears, double rate) {
        if (hasNotValidInputParameters(principalAmount, mortgageDurationInYears, rate)) {
            throw new InvalidInputException("Negative values are not allowed");
        }
    }

    private static boolean hasNotValidInputParameters(int principalAmount, int mortgageTerm, double rate) {
        return isNegative(principalAmount) || isNegativeOrZero(mortgageTerm) || isNegative(rate);
    }

    private static boolean isNegative(double number) {
        return number < 0;
    }

    private static boolean isNegativeOrZero(int number) {
        return number <= 0;
    }

    private static double convertPercentToDecimal(double rate) {
        return rate / PERCENT_RATIO_CONVERTER;
    }

    private static int convertYearsToMonth(int years) {
        return years * MONTHS_IN_YEAR;
    }

    private static double calculatePayment(int principalAmount, double decimalRate, int mortgageDurationInMonth) {
        return isZeroRate(decimalRate) ?
                calculatePaymentForZeroRate(principalAmount, mortgageDurationInMonth) :
                calculatePaymentForNormalRate(principalAmount, decimalRate, mortgageDurationInMonth);
    }

    private static boolean isZeroRate(double decimalRate) {
        return decimalRate == 0;
    }

    private static double calculatePaymentForZeroRate(double principalAmount, int mortgageDurationInMonth) {
        return principalAmount / mortgageDurationInMonth;
    }

    private static double calculatePaymentForNormalRate(int principalAmount, double decimalRate, int mortgageDurationInMonth) {

        return principalAmount * calculateAnnuityRatio(mortgageDurationInMonth, decimalRate);
    }

    private static double calculateAnnuityRatio(int mortgageDurationInMonth, double decimalRate) {
        double monthlyRate = convertYearsRateToMonthlyRate(decimalRate);

        return monthlyRate / (1 - Math.pow(1 + monthlyRate, -mortgageDurationInMonth));
    }

    private static double convertYearsRateToMonthlyRate(double yearsRate) {
        return yearsRate / MONTHS_IN_YEAR;
    }
}
