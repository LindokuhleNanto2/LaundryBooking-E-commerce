/**
 * Booking.java
 * Author: Snalo (230541844)
 * Date: 25 July 2026
 */
/*
*Booking.java
* Muso Nkuntsu
* 231223722
* Date: 25 July 2026
*  */

package com.cput.laundryecommercebookingsystem.domain;

import com.cput.laundryecommercebookingsystem.domain.enums.BookingStatus;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Objects;

// Still not done with my Code, waitin gon the UML to know the relationship.
@Entity
@Table(name = "booking")
public class Booking {

    private static final Logger log = LoggerFactory.getLogger(Booking.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status;

    @Column(name  = "total_amount" , nullable = false)
    private double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", nullable = false)
    private LauderyMachine laundryMachine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_slot_id", nullable = false)
    private TimeSlot timeSlot;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name  = "service_id")
    private LaundryService laundryService;

    protected Booking(){}


    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingDate=" + bookingDate +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", student=" + student +
                ", laundryMachine=" + laundryMachine +
                ", timeSlot=" + timeSlot +
                ", laundryService=" + laundryService +
                '}';
    }

    private Booking(Builder builder){
        this.id = builder.id;
        this.status = builder.status;
        this.bookingDate = builder.bookingDate;
        this.laundryMachine = builder.laundryMachine;
        this.totalAmount = builder.totalAmount;
        this.student = builder.student;
        this.laundryMachine = builder.laundryMachine;
        this.timeSlot = builder.timeSlot;
    }
    public static Builder builder(){
        return new Builder();
    }
    public void createBooking(){
            this.status = BookingStatus.CONFIRMED;
    }
    public void cancelBooking(){
            this.status = BookingStatus.CANCELLED;
    }
    public void  updateStatus(BookingStatus newStatus){
        this.status = newStatus;
    }
    public Long getId() {
        return id;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Student getStudent() {
        return student;
    }

    public LauderyMachine getLaundryMachine() {
        return laundryMachine;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public LaundryService getLaundryService() {
        return laundryService;
    }
    public static class Builder{
        private Long id;
        private LocalDateTime bookingDate;
        private BookingStatus status;
        private double amount;
        private Student student;
        private LauderyMachine laundryMachine;
        private TimeSlot timeSlot;
        private LaundryService laundryService;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setBookingDate(LocalDateTime bookingDate) {
            this.bookingDate = bookingDate;
            return this;
        }

        public Builder setStatus(BookingStatus status) {
            this.status = status;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder setLaundryMachine(LauderyMachine laundryMachine) {
            this.laundryMachine = laundryMachine;
            return this;
        }

        public Builder setTimeSlot(TimeSlot timeSlot) {
            this.timeSlot = timeSlot;
            return this;
        }

        public Builder setLaundryService(LaundryService laundryService) {
            this.laundryService = laundryService;
            return this;
        }
        public Booking build(){
            Objects.requireNonNull(student, "student is required");
            Objects.requireNonNull(laundryMachine, "LaundryMachine is required");
            Objects.requireNonNull(timeSlot, "TimeSlot is required");
            Objects.requireNonNull(bookingDate, "BookingDate is required");
            Objects.requireNonNull(status, "Status is required");
            return new Booking(this);
        }
    }
}

