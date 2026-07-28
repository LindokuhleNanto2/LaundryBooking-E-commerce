package com.cput.laundryecommercebookingsystem.repository;

import com.cput.laundryecommercebookingsystem.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Libolwetu Nokenke 222665963

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(String status);
    List<Payment> findByBookingId(Long bookingId);
    List<Payment> findByOrderId(Long orderId);
    List<Payment> findByServiceId(Long serviceId);
}
