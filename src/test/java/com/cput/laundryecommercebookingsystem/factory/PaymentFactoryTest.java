package com.cput.laundryecommercebookingsystem.factory;

// 222665963 Libolwetu Nokenke
import com.cput.laundryecommercebookingsystem.domain.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFactoryTest {

    @Test
    void createPayment_validInput_returnsPayment() {
        Payment payment = PaymentFactory.createPayment(
                150.00,
                LocalDateTime.now(),
                "Card",
                "Completed",
                "TRX12345",
                1L,
                null,
                null
        );

        assertNotNull(payment);
        assertEquals(150.00, payment.getAmount());
        assertEquals("Card", payment.getPaymentMethod());
        assertEquals("Completed", payment.getStatus());
        assertEquals("TRX12345", payment.getTransactionRef());
        assertEquals(1L, payment.getBookingId());
    }

    @Test
    void createPayment_zeroAmount_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                PaymentFactory.createPayment(
                        0.0,
                        LocalDateTime.now(),
                        "Card",
                        "Completed",
                        "TRX12345",
                        1L,
                        null,
                        null
                )
        );
    }

    @Test
    void createPayment_negativeAmount_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                PaymentFactory.createPayment(
                        -50.0,
                        LocalDateTime.now(),
                        "Card",
                        "Completed",
                        "TRX12345",
                        1L,
                        null,
                        null
                )
        );
    }

    @Test
    void createPayment_nullPaymentDate_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                PaymentFactory.createPayment(
                        150.00,
                        null,
                        "Card",
                        "Completed",
                        "TRX12345",
                        1L,
                        null,
                        null
                )
        );
    }

    @Test
    void createPayment_emptyPaymentMethod_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                PaymentFactory.createPayment(
                        150.00,
                        LocalDateTime.now(),
                        "",
                        "Completed",
                        "TRX12345",
                        1L,
                        null,
                        null
                )
        );
    }

    @Test
    void createPayment_emptyStatus_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                PaymentFactory.createPayment(
                        150.00,
                        LocalDateTime.now(),
                        "Card",
                        "",
                        "TRX12345",
                        1L,
                        null,
                        null
                )
        );
    }

    @Test
    void createPayment_noBookingOrderOrServiceLinked_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                PaymentFactory.createPayment(
                        150.00,
                        LocalDateTime.now(),
                        "Card",
                        "Completed",
                        "TRX12345",
                        null,
                        null,
                        null
                )
        );
    }
}
