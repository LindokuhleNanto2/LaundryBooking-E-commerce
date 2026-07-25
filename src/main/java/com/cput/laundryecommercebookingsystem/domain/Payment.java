package com.cput.laundryecommercebookingsystem.domain;


import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private int paymentId;
    private double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String status;
    private String transactionRef;
    private int bookingId;
    private int orderId;
    private int serviceId;

    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.amount = builder.amount;
        this.paymentDate = builder.paymentDate;
        this.paymentMethod = builder.paymentMethod;
        this.status = builder.status;
        this.transactionRef = builder.transactionRef;
        this.bookingId = builder.bookingId;
        this.orderId = builder.orderId;
        this.serviceId = builder.serviceId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getServiceId() {
        return serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return paymentId == payment.paymentId &&
                Double.compare(payment.amount, amount) == 0 &&
                bookingId == payment.bookingId &&
                orderId == payment.orderId &&
                serviceId == payment.serviceId &&
                Objects.equals(paymentDate, payment.paymentDate) &&
                Objects.equals(paymentMethod, payment.paymentMethod) &&
                Objects.equals(status, payment.status) &&
                Objects.equals(transactionRef, payment.transactionRef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, amount, paymentDate, paymentMethod,
                status, transactionRef, bookingId, orderId, serviceId);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", transactionRef='" + transactionRef + '\'' +
                ", bookingId=" + bookingId +
                ", orderId=" + orderId +
                ", serviceId=" + serviceId +
                '}';
    }

    public static class Builder {
        private int paymentId;
        private double amount;
        private LocalDateTime paymentDate;
        private String paymentMethod;
        private String status;
        private String transactionRef;
        private int bookingId;
        private int orderId;
        private int serviceId;

        public Builder setPaymentId(int paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setPaymentDate(LocalDateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setTransactionRef(String transactionRef) {
            this.transactionRef = transactionRef;
            return this;
        }

        public Builder setBookingId(int bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setServiceId(int serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
