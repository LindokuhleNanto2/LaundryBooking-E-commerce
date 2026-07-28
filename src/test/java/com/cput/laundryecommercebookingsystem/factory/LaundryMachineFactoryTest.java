package com.cput.laundryecommercebookingsystem.factory;

import com.cput.laundryecommercebookingsystem.domain.LaundryMachine;
import com.cput.laundryecommercebookingsystem.domain.LaundryRoom;
import com.cput.laundryecommercebookingsystem.domain.enums.MachineStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Lindokuhle Nanto
 * 240443608
 * 25 July 2026
 */

public class LaundryMachineFactoryTest {

    private LaundryMachineFactory factory;
    private LaundryRoom laundryRoom;


    @BeforeEach
    void setUp() {
        factory = new LaundryMachineFactory();
        laundryRoom = new LaundryRoom();
    }


    @Test
    void shouldCreateLaundryMachineSuccessfully() {

        LaundryMachine machine = factory.create(
                "WM-001",
                "WASHER",
                MachineStatus.AVAILABLE,
                laundryRoom
        );

        assertNotNull(machine);
        assertEquals("WM-001", machine.getMachineNumber());
        assertEquals("WASHER", machine.getType());
        assertEquals(MachineStatus.AVAILABLE, machine.getStatus());
        assertEquals(laundryRoom, machine.getLaundryRoom());
    }


    @Test
    void shouldCreateLaundryMachineWithInUseStatus() {

        LaundryMachine machine = factory.create(
                "DR-001",
                "DRYER",
                MachineStatus.IN_USE,
                laundryRoom
        );

        assertNotNull(machine);
        assertEquals(MachineStatus.IN_USE, machine.getStatus());
    }


    @Test
    void shouldCreateLaundryMachineWithOutOfOrderStatus() {

        LaundryMachine machine = factory.create(
                "WM-002",
                "WASHER",
                MachineStatus.OUT_OF_ORDER,
                laundryRoom
        );

        assertNotNull(machine);
        assertEquals(
                MachineStatus.OUT_OF_ORDER,
                machine.getStatus()
        );
    }


    @Test
    void shouldRejectMissingMachineNumber() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        null,
                        "WASHER",
                        MachineStatus.AVAILABLE,
                        laundryRoom
                )
        );
    }


    @Test
    void shouldRejectMissingMachineType() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        "WM-001",
                        null,
                        MachineStatus.AVAILABLE,
                        laundryRoom
                )
        );
    }


    @Test
    void shouldRejectNullMachineStatus() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        "WM-001",
                        "WASHER",
                        null,
                        laundryRoom
                )
        );
    }


    @Test
    void shouldRejectNullLaundryRoom() {

        assertThrows(
                IllegalArgumentException.class,
                () -> factory.create(
                        "WM-001",
                        "WASHER",
                        MachineStatus.AVAILABLE,
                        null
                )
        );
    }
}

