package com.cput.laundryecommercebookingsystem.service.impl;

import com.cput.laundryecommercebookingsystem.domain.LaundryRoom;
import com.cput.laundryecommercebookingsystem.domain.Machine;
import com.cput.laundryecommercebookingsystem.factory.LaundryRoomFactory;
import com.cput.laundryecommercebookingsystem.repository.LaundryRoomRepository;
import com.cput.laundryecommercebookingsystem.service.LaundryRoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *  * Talent Nocuze
 *  * 230405886
 *  * 25 July 2026
 *  */

@Service
public class LaundryRoomServiceImpl implements LaundryRoomService {
    private final LaundryRoomRepository laundryRoomRepository;

    public LaundryRoomServiceImpl(LaundryRoomRepository laundryRoomRepository) {
        this.laundryRoomRepository = laundryRoomRepository;
    }

    @Override
    @Transactional
    public LaundryRoom createRoom(String roomNumber, String location, int capacity, String description) {
        LaundryRoom room = LaundryRoomFactory.createLaundryRoom(roomNumber, location, capacity, description);
        return laundryRoomRepository.save(room);
    }

    @Override
    @Transactional
    public LaundryRoom activateRoom(int roomId) {
        LaundryRoom room = getRoomOrThrow(roomId);
        room.addRoom();
        return laundryRoomRepository.save(room);
    }

    @Override
    @Transactional
    public LaundryRoom deactivateRoom(int roomId) {
        LaundryRoom room = getRoomOrThrow(roomId);
        room.deactivateRoom();
        return laundryRoomRepository.save(room);
    }

    @Override
    @Transactional
    public LaundryRoom updateRoom(int roomId, String location, int capacity, String description) {
        LaundryRoom room = getRoomOrThrow(roomId);
        room.updateRoom(location, capacity, description);
        return laundryRoomRepository.save(room);
    }

    @Override
    @Transactional
    public LaundryRoom addMachineToRoom(int roomId, Machine machine) {
        LaundryRoom room = getRoomOrThrow(roomId);
        room.addMachine(machine);
        return laundryRoomRepository.save(room);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LaundryRoom> getRoomById(int roomId) {
        return laundryRoomRepository.findById(roomId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LaundryRoom> getActiveRooms() {
        return laundryRoomRepository.findByIsActive(true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LaundryRoom> getAllRooms() {
        return laundryRoomRepository.findAll();
    }

    private LaundryRoom getRoomOrThrow(int roomId) {
        return laundryRoomRepository.findById(roomId)
                .orElseThrow(() -> new NoSuchElementException("LaundryRoom not found with id: " + roomId));
    }


}
