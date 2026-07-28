package com.cput.laundryecommercebookingsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Lindokuhle Nanto
 * 240443608
 * 25 July 2026
 */


@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private LaundryService laundryService;
    private int rating;
    private String comment;
    private LocalDateTime date;
    protected Review() {
    }


    private Review(Builder builder) {
        this.student = builder.student;
        this.laundryService = builder.laundryService;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.date = builder.date;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public LaundryService getLaundryService() {
        return laundryService;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getDate() {
        return date;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Review)) {
            return false;
        }

        Review review = (Review) o;

        return id != null && Objects.equals(id, review.id);
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }


    public static class Builder {

        private Student student;
        private LaundryService laundryService;
        private int rating;
        private String comment;
        private LocalDateTime date;


        public Builder student(Student student) {
            this.student = student;
            return this;
        }

        public Builder laundryService(LaundryService laundryService) {
            this.laundryService = laundryService;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder date(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Review build() {

            if (student == null) {
                throw new IllegalArgumentException(
                        "Student is required"
                );
            }

            if (laundryService == null) {
                throw new IllegalArgumentException(
                        "Laundry service is required"
                );
            }

            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException(
                        "Rating must be between 1 and 5"
                );
            }

            if (comment == null || comment.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Review comment is required"
                );
            }

            if (date == null) {
                throw new IllegalArgumentException(
                        "Review date is required"
                );
            }

            return new Review(this);
        }
    }
}