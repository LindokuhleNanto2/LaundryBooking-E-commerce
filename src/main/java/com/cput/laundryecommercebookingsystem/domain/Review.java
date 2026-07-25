package com.cput.laundryecommercebookingsystem.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Review {

    private final int id;
    private final Student student;
    private final LaundryService target;
    private final int rating;
    private final String comment;
    private final LocalDateTime date;


    private Review(Builder builder) {
        this.id = builder.id;
        this.student = builder.student;
        this.target = builder.target;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.date = builder.date;
    }


    public int getId() {
        return id;
    }


    public Student getStudent() {
        return student;
    }


    public LaundryService getTarget() {
        return target;
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

        return id == review.id
                && rating == review.rating
                && Objects.equals(student, review.student)
                && Objects.equals(target, review.target)
                && Objects.equals(comment, review.comment)
                && Objects.equals(date, review.date);
    }


    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                student,
                target,
                rating,
                comment,
                date
        );
    }


    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", student=" + student +
                ", target=" + target +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }


    public static class Builder {

        private int id;
        private Student student;
        private LaundryService target;
        private int rating;
        private String comment;
        private LocalDateTime date;


        public Builder(int id, Student student, LaundryService target) {
            this.id = id;
            this.student = student;
            this.target = target;
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
            return new Review(this);
        }
    }
}

