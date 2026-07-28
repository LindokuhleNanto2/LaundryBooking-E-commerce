package com.cput.laundryecommercebookingsystem.service.impl;

import com.cput.laundryecommercebookingsystem.domain.Payment;
import com.cput.laundryecommercebookingsystem.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Libolwetu Nokenke 222665963


@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentServiceImpl paymentService;
    private Payment payment;
    @BeforeEach
    void setUp() {
        payment = Payment.builder()
                .setPaymentId(1L)
                .setAmount(150.00)
                .setPaymentDate(LocalDateTime.now())
                .setPaymentMethod("Card")
                .setStatus("Pending")
                .setTransactionRef("LR001")
                .setBookingId(1L)
                .build();
    }

    @Test
    void processPayment_validInput_savesAndReturnsPayment() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.processPayment(150.00, LocalDateTime.now(), "Card",
                "Pending", "LR001", 1L, null, null);

        assertNotNull(result);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }
    @Test
    void markAsCompleted_existingPayment_updatesStatus() {
        Payment completed = Payment.builder()
                .setPaymentId(1L)
                .setAmount(150.00)
                .setPaymentDate(payment.getPaymentDate())
                .setPaymentMethod("Card")
                .setStatus("Completed")
                .setTransactionRef("LR001")
                .setBookingId(1L)
                .build();
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        when(paymentRepository.save(any(Payment.class))).thenReturn(completed);

        Payment result = paymentService.markAsCompleted(1L);

        assertEquals("Completed", result.getStatus());
    }
    @Test
    void markAsCompleted_nonExistingPayment_throwsException() {
        when(paymentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> paymentService.markAsCompleted(99L));
    }
    @Test
    void markAsFailed_existingPayment_updatesStatus() {
        Payment failed = Payment.builder()
                .setPaymentId(1L)
                .setAmount(150.00)
                .setPaymentDate(payment.getPaymentDate())
                .setPaymentMethod("Card")
                .setStatus("Failed")
                .setTransactionRef("LR001")
                .setBookingId(1L)
                .build();
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));
        when(paymentRepository.save(any(Payment.class))).thenReturn(failed);

        Payment result = paymentService.markAsFailed(1L);

        assertEquals("Failed", result.getStatus());
    }
    @Test
    void getPaymentById_existingId_returnsPayment() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        Optional<Payment> result = paymentService.getPaymentById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getPaymentId());
    }
    @Test
    void getPaymentById_nonExistingId_returnsEmptyOptional() {
        when(paymentRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Payment> result = paymentService.getPaymentById(99L);

        assertTrue(result.isEmpty());
    }
    @Test
    void getPaymentsByBookingId_returnsMatchingPayments() {
        when(paymentRepository.findByBookingId(1L)).thenReturn(Collections.singletonList(payment));

        List<Payment> results = paymentService.getPaymentsByBookingId(1L);

        assertEquals(1, results.size());
    }
    @Test
    void getPaymentsByStatus_returnsMatchingPayments() {
        when(paymentRepository.findByStatus("Pending")).thenReturn(Collections.singletonList(payment));

        List<Payment> results = paymentService.getPaymentsByStatus("Pending");

        assertEquals(1, results.size());
    }
    @Test
    void getAllPayments_returnsAllPayments() {
        when(paymentRepository.findAll()).thenReturn(Collections.singletonList(payment));

        List<Payment> results = paymentService.getAllPayments();

        assertEquals(1, results.size());
    }
}
