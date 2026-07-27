package com.cput.laundryecommercebookingsystem.factory;

import com.cput.laundryecommercebookingsystem.domain.LaundryService;
import com.cput.laundryecommercebookingsystem.domain.Review;
import com.cput.laundryecommercebookingsystem.domain.Student;
import java.time.LocalDateTime;

/**
 * Lindokuhle Nanto
 * 240443608
 * 25 July 2026
 */

public class ReviewFactory {
    public Review create(
            Student student,
            LaundryService laundryService,
            int rating,
            String comment,
            LocalDateTime date) {

        return Review.builder()
                .student(student)
                .laundryService(laundryService)
                .rating(rating)
                .comment(comment)
                .date(date)
                .build();
    }
}

