package com.cput.laundryecommercebookingsystem.factory;

import com.cput.laundryecommercebookingsystem.domain.Order;
import com.cput.laundryecommercebookingsystem.domain.OrderItem;
import com.cput.laundryecommercebookingsystem.domain.enums.OrderStatus;
import com.cput.laundryecommercebookingsystem.domain.Student;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Talent Nocuze
 * 230405886
 * 25 July 2026
 */

public final class OrderFactory {

    private OrderFactory() {
    }
    public static Order createOrder(Student student, List<OrderItem> orderItems, double totalAmount) {
        return createOrder(student, orderItems, totalAmount, LocalDateTime.now(), OrderStatus.PENDING);
    }

    public static Order createOrder(Student student,
                                    List<OrderItem> orderItems,
                                    double totalAmount,
                                    LocalDateTime orderDate,
                                    OrderStatus status) {

        if (student == null) {
            throw new IllegalArgumentException("Order cannot be created without a Student.");
        }
        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("Order cannot be created without at least one OrderItem.");
        }
        if (totalAmount < 0) {
            throw new IllegalArgumentException("Total amount must not be negative.");
        }
        if (orderDate == null) {
            throw new IllegalArgumentException("Order date must not be null.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Order status must not be null.");
        }

        return new Order.Builder()
                .studentId(student)
                .orderItems(orderItems)
                .totalAmount(totalAmount)
                .orderDate(orderDate)
                .status(status)
                .build();
    }
}
