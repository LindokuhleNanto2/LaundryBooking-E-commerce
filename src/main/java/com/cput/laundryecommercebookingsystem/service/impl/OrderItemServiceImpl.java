package com.cput.laundryecommercebookingsystem.service.impl;

import com.cput.laundryecommercebookingsystem.domain.Order;
import com.cput.laundryecommercebookingsystem.domain.OrderItem;
import com.cput.laundryecommercebookingsystem.domain.Product;
import com.cput.laundryecommercebookingsystem.repository.OrderItemRepository;
import com.cput.laundryecommercebookingsystem.service.OrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/*OrderItemServiceImpl.java
 * Author: Sabotseng Ndaba (230235875)
 * Date: 28 July 2026
 */

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional
    public OrderItem updateOrderItem(OrderItem orderItem) {
        if (!orderItemRepository.existsById(orderItem.getOrderItemId())) {
            throw new NoSuchElementException("OrderItem not found.");
        }
        return orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional
    public void deleteOrderItem(int orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderItem> getOrderItemById(int orderItemId) {
        return orderItemRepository.findById(orderItemId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> getOrderItemsByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> getOrderItemsByProduct(Product product) {
        return orderItemRepository.findByProduct(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
}
