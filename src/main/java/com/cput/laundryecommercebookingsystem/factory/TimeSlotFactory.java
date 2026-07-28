package com.cput.laundryecommercebookingsystem.factory;

// 222665963 Libolwetu Nokenke
import com.cput.laundryecommercebookingsystem.domain.TimeSlot;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlotFactory {

    public static TimeSlot createTimeSlot(LocalTime startTime, LocalTime endTime,
                                          LocalDate date, boolean isAvailable) {

        if (startTime == null) {
            throw new IllegalArgumentException("Start time cannot be null");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("End time cannot be null");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        return TimeSlot.builder()
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setDate(date)
                .setAvailable(isAvailable)
                .build();
    }
}
