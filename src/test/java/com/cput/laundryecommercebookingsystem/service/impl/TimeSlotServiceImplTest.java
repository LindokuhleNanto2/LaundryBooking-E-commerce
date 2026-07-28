package com.cput.laundryecommercebookingsystem.service.impl;

import com.cput.laundryecommercebookingsystem.domain.TimeSlot;
import com.cput.laundryecommercebookingsystem.repository.TimeSlotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


  //Libolwetu Nokenke 222665963
@ExtendWith(MockitoExtension.class)
class TimeSlotServiceImplTest {

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @InjectMocks
    private TimeSlotServiceImpl timeSlotService;

    private TimeSlot timeSlot;
    @BeforeEach
    void setUp() {
        timeSlot = TimeSlot.builder()
                .setId(1L)
                .setStartTime(LocalTime.of(9, 0))
                .setEndTime(LocalTime.of(10, 0))
                .setDate(LocalDate.now().plusDays(1))
                .setAvailable(true)
                .build();
    }
    @Test
    void createTimeSlot_validInput_savesAndReturnsTimeSlot() {
        when(timeSlotRepository.save(any(TimeSlot.class))).thenReturn(timeSlot);

        TimeSlot result = timeSlotService.createTimeSlot(
                LocalTime.of(9, 0), LocalTime.of(10, 0), LocalDate.now().plusDays(1));

        assertNotNull(result);
        verify(timeSlotRepository, times(1)).save(any(TimeSlot.class));
    }
    @Test
    void markAsBooked_existingTimeSlot_setsUnavailable() {
        TimeSlot booked = TimeSlot.builder()
                .setId(1L)
                .setStartTime(timeSlot.getStartTime())
                .setEndTime(timeSlot.getEndTime())
                .setDate(timeSlot.getDate())
                .setAvailable(false)
                .build();
        when(timeSlotRepository.findById(1L)).thenReturn(Optional.of(timeSlot));
        when(timeSlotRepository.save(any(TimeSlot.class))).thenReturn(booked);

        TimeSlot result = timeSlotService.markAsBooked(1L);

        assertFalse(result.isAvailable());
    }
    @Test
    void markAsBooked_nonExistingTimeSlot_throwsException() {
        when(timeSlotRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> timeSlotService.markAsBooked(99L));
    }
    @Test
    void markAsAvailable_existingTimeSlot_setsAvailable() {
        TimeSlot unavailable = TimeSlot.builder()
                .setId(1L)
                .setStartTime(timeSlot.getStartTime())
                .setEndTime(timeSlot.getEndTime())
                .setDate(timeSlot.getDate())
                .setAvailable(false)
                .build();
        TimeSlot available = TimeSlot.builder()
                .setId(1L)
                .setStartTime(timeSlot.getStartTime())
                .setEndTime(timeSlot.getEndTime())
                .setDate(timeSlot.getDate())
                .setAvailable(true)
                .build();
        when(timeSlotRepository.findById(1L)).thenReturn(Optional.of(unavailable));
        when(timeSlotRepository.save(any(TimeSlot.class))).thenReturn(available);

        TimeSlot result = timeSlotService.markAsAvailable(1L);

        assertTrue(result.isAvailable());
    }
    @Test
    void getTimeSlotById_existingId_returnsTimeSlot() {
        when(timeSlotRepository.findById(1L)).thenReturn(Optional.of(timeSlot));

        Optional<TimeSlot> result = timeSlotService.getTimeSlotById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }
    @Test
    void getTimeSlotById_nonExistingId_returnsEmptyOptional() {
        when(timeSlotRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<TimeSlot> result = timeSlotService.getTimeSlotById(99L);

        assertTrue(result.isEmpty());
    }
    @Test
    void getAvailableTimeSlots_returnsAvailableSlots() {
        when(timeSlotRepository.findByIsAvailable(true)).thenReturn(Collections.singletonList(timeSlot));

        List<TimeSlot> results = timeSlotService.getAvailableTimeSlots();

        assertEquals(1, results.size());
    }
    @Test
    void getTimeSlotsByDate_returnsMatchingSlots() {
        when(timeSlotRepository.findByDate(timeSlot.getDate())).thenReturn(Collections.singletonList(timeSlot));

        List<TimeSlot> results = timeSlotService.getTimeSlotsByDate(timeSlot.getDate());

        assertEquals(1, results.size());
    }
    @Test
    void getAllTimeSlots_returnsAllSlots() {
        when(timeSlotRepository.findAll()).thenReturn(Collections.singletonList(timeSlot));

        List<TimeSlot> results = timeSlotService.getAllTimeSlots();

        assertEquals(1, results.size());
    }
}
