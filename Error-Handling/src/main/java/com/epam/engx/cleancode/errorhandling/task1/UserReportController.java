package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.models.Report;

public class UserReportController {

    private static final String USER_TOTAL_AMOUNT_TYPE = "userTotal";
    private UserReportBuilder userReportBuilder;

    public Report getUserTotalOrderAmountReport(String userId){
        Report report = new Report();
        report.setAmountType(USER_TOTAL_AMOUNT_TYPE);
        try {
            report.setAmount(getUserTotalAmount(userId));
        } catch (InvalidDaoException | ReportBuilderException e) {
            report.setErrorMessage(e.getMessage());
        }
        return report;
    }

    private double getUserTotalAmount(String userId) {
        return userReportBuilder.getUserTotalOrderAmount(userId);
    }

    public UserReportBuilder getUserReportBuilder() {
        return userReportBuilder;
    }

    public void setUserReportBuilder(UserReportBuilder userReportBuilder) {
        this.userReportBuilder = userReportBuilder;
    }
}
