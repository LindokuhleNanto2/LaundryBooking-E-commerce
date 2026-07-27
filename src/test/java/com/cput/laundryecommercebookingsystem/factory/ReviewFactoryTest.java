package com.cput.laundryecommercebookingsystem.factory;

import com.cput.laundryecommercebookingsystem.domain.LaundryService;
import com.cput.laundryecommercebookingsystem.domain.Review;
import com.cput.laundryecommercebookingsystem.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lindokuhle Nanto
 * 240443608
 * 25 July 2026
 */

public class ReviewFactoryTest {

    private ReviewFactory factory;
    private Student student;
    private LaundryService laundryService;
    private LocalDateTime reviewDate;

    /**
     * Creates test objects before each test.
     */
    @BeforeEach
    void setUp() {
        factory = new ReviewFactory();
        student = new Student();
        laundryService = new LaundryService();
        reviewDate = LocalDateTime.now();
    }

    /**
     * Tests that a Review is successfully created.
     */
    @Test
    void shouldCreateReviewSuccessfully() {

        Review review = factory.create(
                student,
                laundryService,
                5,
                "Excellent laundry service!",
                reviewDate
        );

        assertNotNull(review);
        assertEquals(student, review.getStudent());
        assertEquals(
                laundryService,
                review.getLaundryService()
        );
        assertEquals(5, review.getRating());
        assertEquals(
                "Excellent laundry service!",
                review.getComment()
        );
        assertEquals(reviewDate, review.getDate());
    }

    /**
     * Tests that the minimum valid rating of 1 is accepted.
     */
    @Test
    void shouldAcceptMinimumValidRating() {

        Review review = factory.create(
                student,
                laundryService,
                1,
                "Poor service.",
                reviewDate
        );

        assertEquals(1, review.getRating());
    }

    /**
     * Tests that the maximum valid rating of 5 is accepted.
     */
    @Test
    void shouldAcceptMaximumValidRating() {

        Review review = factory.create(
                student,
                laundryService,
                5,
                "Excellent service.",
                reviewDate
        );

        assertEquals(5, review.getRating());
    }

    /**
     * Tests that a rating below 1 is rejected.
     */
    @Test
    void shouldRejectRatingBelowOne() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        student,
                        laundryService,
                        0,
                        "Invalid rating.",
                        reviewDate
                )
        );
    }

    /**
     * Tests that a rating above 5 is rejected.
     */
    @Test
    void shouldRejectRatingAboveFive() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        student,
                        laundryService,
                        6,
                        "Invalid rating.",
                        reviewDate
                )
        );
    }

    /**
     * Tests that a null student is rejected.
     */
    @Test
    void shouldRejectNullStudent() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        null,
                        laundryService,
                        5,
                        "Good service.",
                        reviewDate
                )
        );
    }

    /**
     * Tests that a null laundry service is rejected.
     */
    @Test
    void shouldRejectNullLaundryService() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        student,
                        null,
                        5,
                        "Good service.",
                        reviewDate
                )
        );
    }

    /**
     * Tests that a null comment is rejected.
     */
    @Test
    void shouldRejectNullComment() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        student,
                        laundryService,
                        5,
                        null,
                        reviewDate
                )
        );
    }

    /**
     * Tests that an empty comment is rejected.
     */
    @Test
    void shouldRejectEmptyComment() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        student,
                        laundryService,
                        5,
                        "   ",
                        reviewDate
                )
        );
    }

    /**
     * Tests that a null review date is rejected.
     */
    @Test
    void shouldRejectNullDate() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        student,
                        laundryService,
                        5,
                        "Good service.",
                        null
                )
        );
    }
}

