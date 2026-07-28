package com.cput.laundryecommercebookingsystem.factory;

// 222665963 Libolwetu Nokenke
import com.cput.laundryecommercebookingsystem.domain.TimeSlot;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotFactoryTest {

    @Test
    void createTimeSlot_validInput_returnsTimeSlot() {
        TimeSlot timeSlot = TimeSlotFactory.createTimeSlot(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalDate.now().plusDays(1),
                true
        );

        assertNotNull(timeSlot);
        assertEquals(LocalTime.of(9, 0), timeSlot.getStartTime());
        assertEquals(LocalTime.of(10, 0), timeSlot.getEndTime());
        assertTrue(timeSlot.isAvailable());
    }

    @Test
    void createTimeSlot_nullStartTime_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                TimeSlotFactory.createTimeSlot(
                        null,
                        LocalTime.of(10, 0),
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }

    @Test
    void createTimeSlot_nullEndTime_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                TimeSlotFactory.createTimeSlot(
                        LocalTime.of(9, 0),
                        null,
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }

    @Test
    void createTimeSlot_nullDate_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                TimeSlotFactory.createTimeSlot(
                        LocalTime.of(9, 0),
                        LocalTime.of(10, 0),
                        null,
                        true
                )
        );
    }

    @Test
    void createTimeSlot_endTimeBeforeStartTime_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                TimeSlotFactory.createTimeSlot(
                        LocalTime.of(10, 0),
                        LocalTime.of(9, 0),
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }

    @Test
    void createTimeSlot_endTimeEqualsStartTime_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                TimeSlotFactory.createTimeSlot(
                        LocalTime.of(9, 0),
                        LocalTime.of(9, 0),
                        LocalDate.now().plusDays(1),
                        true
                )
        );
    }
}
