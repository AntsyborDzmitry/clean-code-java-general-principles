package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.models.Report;

public class UserReportController {

    private static final String USER_TOTAL_AMOUNT_TYPE = "userTotal";
    private UserReportBuilder userReportBuilder;

    public Report getUserTotalOrderAmountReport(String userId) {
        double amount = 0d;
        String errorMessage = "";

        try {
            amount = userReportBuilder.getUserTotalOrderAmount(userId);
        } catch (InvalidDaoException | ReportBuilderException e) {
            errorMessage = e.getMessage();
        }

        return new Report(amount, USER_TOTAL_AMOUNT_TYPE, errorMessage);
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
