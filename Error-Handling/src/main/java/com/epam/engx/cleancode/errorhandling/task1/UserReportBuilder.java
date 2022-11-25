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

    public Double getUserTotalOrderAmount(String userId) {
        validateDao();
        User user = getUserFromDaoById(userId);
        List<Order> orders = getUserOrders(user);

        return getSumOfTotalFromOrders(orders);
    }

    private void validateDao() {
        if (userDao == null) {
            throw new InvalidDaoException(TECHNICAL_ERROR_MESSAGE);
        }
    }

    private User getUserFromDaoById(String userId) {
        User user = userDao.getUser(userId);
        validateUser(user);
        return user;
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new ReportBuilderException(USER_ID_NOT_EXIST_WARNING_MESSAGE);
        }
    }

    private List<Order> getUserOrders(User user) {
        List<Order> orders = user.getAllOrders();
        validateOrders(orders);
        return orders;
    }

    private void validateOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            throw new ReportBuilderException(NO_SUBMITTED_ORDERS_WARNING_MESSAGE);
        }
    }

    private Double getSumOfTotalFromOrders(List<Order> orders) {
        Double sum = 0.0;
        for (Order order : orders) {
            sum += getTotalFromOrder(order);
        }
        return sum;
    }

    private Double getTotalFromOrder(Order order) {
        if (order.isSubmitted()) {
            Double total = order.total();
            validateTotal(total);
            return total;
        }
        return 0d;
    }

    private void validateTotal(Double total) {
        if (total < 0) {
            throw new ReportBuilderException(WRONG_ORDER_AMOUNT_ERROR_MESSAGE);
        }
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
