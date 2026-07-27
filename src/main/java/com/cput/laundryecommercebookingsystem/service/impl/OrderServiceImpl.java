package com.cput.laundryecommercebookingsystem.service.impl;

import com.cput.laundryecommercebookingsystem.domain.Order;
import com.cput.laundryecommercebookingsystem.domain.OrderItem;
import com.cput.laundryecommercebookingsystem.domain.Student;
import com.cput.laundryecommercebookingsystem.domain.enums.OrderStatus;
import com.cput.laundryecommercebookingsystem.factory.OrderFactory;
import com.cput.laundryecommercebookingsystem.repository.OrderRepository;
import com.cput.laundryecommercebookingsystem.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *  * Talent Nocuze
 *  * 230405886
 *  * 25 July 2026
 *  */

@Service
public class OrderServiceImpl implements OrderService { private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order createOrder(Student student, List<OrderItem> orderItems, double totalAmount) {
        Order order = OrderFactory.createOrder(student, orderItems, totalAmount);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order placeOrder(int orderId) {
        Order order = getOrderOrThrow(orderId);
        order.placeOrder();
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order cancelOrder(int orderId) {
        Order order = getOrderOrThrow(orderId);
        order.cancelOrder();
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrderStatus(int orderId, OrderStatus newStatus) {
        Order order = getOrderOrThrow(orderId);
        order.updateStatus(newStatus);
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByStudent(int studentId) {
        return orderRepository.findByStudentPrimaryKey(studentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private Order getOrderOrThrow(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found with id: " + orderId));
    }

}
