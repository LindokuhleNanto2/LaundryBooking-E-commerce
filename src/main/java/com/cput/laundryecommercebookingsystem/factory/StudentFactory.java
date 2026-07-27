package com.cput.laundryecommercebookingsystem.factory;

import com.cput.laundryecommercebookingsystem.domain.Student;

import java.time.LocalDateTime;

/*StudentFactory.java
 * Factory class for creating Student objects.
 * Author: Sabotseng Ndaba (230235875)
 * Date: 26 July 2026
 */

public class StudentFactory {

    public static Student createStudent(String firstName,
                                        String lastName,
                                        String email,
                                        String phoneNumber,
                                        String password) {

        if (firstName == null || firstName.isBlank()) {
            return null;
        }
        if (lastName == null || lastName.isBlank()) {
            return null;
        }
        if (email == null || email.isBlank()) {
            return null;
        }
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return null;
        }
        if (password == null || password.isBlank()) {
            return null;
        }

        return new Student.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setPassword(password)
                .setCreatedAt(LocalDateTime.now())
                .build();
    }
}