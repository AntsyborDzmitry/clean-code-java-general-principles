package com.epam.engx.cleancode.dry.task1;

import com.epam.engx.cleancode.dry.task1.thirdpartyjar.Profitable;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator implements Profitable {

    private static final int SENIOR_AGE_TRESHOLD = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;
    private static final int LEAP_YEAR_SHIFT = 1;
    private static final int PERCENT_RATIO_CONVERTER = 100;

    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        return hasAccountBonus(accountDetails) ? BigDecimal.valueOf(evaluateInterest(accountDetails)) : BigDecimal.ZERO;
    }

    private boolean hasAccountBonus(AccountDetails accountDetails) {
        return getAccountAge(accountDetails) > BONUS_AGE;
    }

    private int getAccountAge(AccountDetails accountDetails) {
        return getYearsNumberBetweenDate(accountDetails.getBirth(), accountDetails.getStartDate());
    }

    private int getYearsNumberBetweenDate(Date from, Date to) {
        Calendar start = setupCalendar(from);
        Calendar end = setupCalendar(to);

        int diffYear = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        return hasPeriodLeapYear(start, end) ? diffYear - 1 : diffYear;
    }

    private Calendar setupCalendar(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    private boolean hasPeriodLeapYear(Calendar startCalendar, Calendar endCalendar) {
        return endCalendar.get(Calendar.DAY_OF_YEAR) + LEAP_YEAR_SHIFT < startCalendar.get(Calendar.DAY_OF_YEAR);
    }

    private double evaluateInterest(AccountDetails accountDetails) {
        if (isAccountReachSeniorAge(accountDetails)) {
            return getInterestBasedOnPercent(accountDetails, SENIOR_PERCENT);
        }
        return getInterestBasedOnPercent(accountDetails, INTEREST_PERCENT);
    }

    private boolean isAccountReachSeniorAge(AccountDetails accountDetails) {
        return calculateAge(accountDetails) >= SENIOR_AGE_TRESHOLD;
    }

    private int calculateAge(AccountDetails accountDetails) {
        return getYearsNumberBetweenDate(accountDetails.getBirth(), new Date());
    }

    private double getInterestBasedOnPercent(AccountDetails accountDetails, double percent) {
        double balance = accountDetails.getBalance().doubleValue();
        int daysFormAccountCreation = getYearsNumberBetweenDate(accountDetails.getStartDate(), new Date());

        return balance * daysFormAccountCreation * percent / PERCENT_RATIO_CONVERTER;
    }
}
