package com.cput.laundryecommercebookingsystem.service;
import com.cput.laundryecommercebookingsystem.domain.LaundryRoom;
import com.cput.laundryecommercebookingsystem.domain.Machine;

import java.util.List;
import java.util.Optional;
/**
 *  * Talent Nocuze
 *  * 230405886
 *  * 25 July 2026
 *  */

public interface LaundryRoomService {

    LaundryRoom createRoom(String roomNumber, String location, int capacity, String description);

    LaundryRoom activateRoom(int roomId);

    LaundryRoom deactivateRoom(int roomId);

    LaundryRoom updateRoom(int roomId, String location, int capacity, String description);

    LaundryRoom addMachineToRoom(int roomId, Machine machine);

    Optional<LaundryRoom> getRoomById(int roomId);

    List<LaundryRoom> getActiveRooms();

    List<LaundryRoom> getAllRooms();
}
