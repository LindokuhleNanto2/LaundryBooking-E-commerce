package com.cput.laundryecommercebookingsystem.factory;

import com.cput.laundryecommercebookingsystem.domain.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*StudentFactoryTest.java
 * Unit tests for StudentFactory
 * Author: Sabotseng Ndaba (230235875)
 * Date: 26 July 2026
 */

class StudentFactoryTest {

    @Test
    void testCreateStudentSuccess() {

        Student student = StudentFactory.createStudent(
                "Katlego",
                "Ndaba",
                "katlegondaba08@gmail.com",
                "0625067447",
                "Ndaba447"
        );

        assertNotNull(student);
        assertEquals("Katlego", student.getFirstName());
        assertEquals("Ndaba", student.getLastName());
        assertEquals("katlegondaba08@gmail.com", student.getEmail());
        assertEquals("0625067447", student.getPhoneNumber());
        assertEquals("Ndaba447", student.getPassword());
        assertNotNull(student.getCreatedAt());
    }

    @Test
    void testCreateStudentWithEmptyFirstName() {

        Student student = StudentFactory.createStudent(
                "",
                "Ndaba",
                "katlegondaba08@gmail.com",
                "0625067447",
                "Ndaba447"
        );

        assertNull(student);
    }

    @Test
    void testCreateStudentWithEmptyLastName() {

        Student student = StudentFactory.createStudent(
                "Katlego",
                "",
                "katlegondaba08@gmail.com",
                "0625067447",
                "Ndaba447"
        );

        assertNull(student);
    }

    @Test
    void testCreateStudentWithEmptyEmail() {

        Student student = StudentFactory.createStudent(
                "Katlego",
                "Ndaba",
                "",
                "0625067447",
                "Ndaba447"
        );

        assertNull(student);
    }

    @Test
    void testCreateStudentWithEmptyPhoneNumber() {

        Student student = StudentFactory.createStudent(
                "Katlego",
                "Ndaba",
                "katlegondaba08@gmail.com",
                "",
                "Ndaba447"
        );

        assertNull(student);
    }

    @Test
    void testCreateStudentWithEmptyPassword() {

        Student student = StudentFactory.createStudent(
                "Katlego",
                "Ndaba",
                "katlegondaba08@gmail.com",
                "0625067447",
                ""
        );

        assertNull(student);
    }
}


