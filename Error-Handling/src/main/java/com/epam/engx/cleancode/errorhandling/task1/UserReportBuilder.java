package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

public class UserReportBuilder {

    private static final String TECHNICAL_ERROR_MESSAGE = "technicalError";
    private static final String USER_ID_NOT_EXIST_WARNING_MESSAGE = "WARNING: User ID doesn't exist.";
    private static final String NO_SUBMITTED_ORDERS_WARNING_MESSAGE = "WARNING: User have no submitted orders.";
    private static final String WRONG_ORDER_AMOUNT_ERROR_MESSAGE = "ERROR: Wrong order amount.";

    private UserDao userDao;

    public double getUserTotalOrderAmount(String userId) {

        validateUserDaoOrThrow(userDao);

        User user = userDao.getUser(userId);
        validateUserOrThrow(user);

        List<Order> orders = user.getAllOrders();
        validateOrdersOrThrow(orders);

        validateOrdersOnTotal(orders);

        return getSumOfTotalFromOrders(orders);
    }

    private void validateUserDaoOrThrow(UserDao userDao) {
        if (userDao == null) {
            throw new InvalidDaoException(TECHNICAL_ERROR_MESSAGE);
        }
    }

    private void validateUserOrThrow(User user) {
        if (user == null) {
            throw new ReportBuilderException(USER_ID_NOT_EXIST_WARNING_MESSAGE);
        }
    }

    private void validateOrdersOrThrow(List<Order> orders) {
        if (orders.isEmpty()) {
            throw new ReportBuilderException(NO_SUBMITTED_ORDERS_WARNING_MESSAGE);
        }
    }

    private void validateOrdersOnTotal(List<Order> orders) {
        for (Order order : orders) {
            if (order.isSubmitted()) {
                validateTotal(order.total());
            }
        }
    }

    private void validateTotal(double total) {
        if (total < 0) {
            throw new ReportBuilderException(WRONG_ORDER_AMOUNT_ERROR_MESSAGE);
        }
    }

    private double getSumOfTotalFromOrders(List<Order> orders) {
        double sum = 0.0;
        for (Order order : orders) {
            sum += getTotalFromOrder(order);
        }
        return sum;
    }

    private double getTotalFromOrder(Order order) {
        return order.isSubmitted() ? order.total() : 0d;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
