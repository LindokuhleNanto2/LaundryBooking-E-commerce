package com.cput.laundryecommercebookingsystem.service.impl;

import com.cput.laundryecommercebookingsystem.domain.LaundryRoom;
import com.cput.laundryecommercebookingsystem.domain.Machine;
import com.cput.laundryecommercebookingsystem.factory.LaundryRoomFactory;
import com.cput.laundryecommercebookingsystem.repository.ILaundryRoomRepository;
import com.cput.laundryecommercebookingsystem.service.ILaundryRoomService;
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
public class LaundryRoomServiceImpl implements ILaundryRoomService {
    private final ILaundryRoomRepository ILaundryRoomRepository;

    public LaundryRoomServiceImpl(ILaundryRoomRepository ILaundryRoomRepository) {
        this.ILaundryRoomRepository = ILaundryRoomRepository;
    }

    @Override
    @Transactional
    public LaundryRoom createRoom(String roomNumber, String location, int capacity, String description) {
        LaundryRoom room = LaundryRoomFactory.createLaundryRoom(roomNumber, location, capacity, description);
        return ILaundryRoomRepository.save(room);
    }

    @Override
    @Transactional
    public LaundryRoom activateRoom(int roomId) {
        LaundryRoom room = getRoomOrThrow(roomId);
        room.addRoom();
        return ILaundryRoomRepository.save(room);
    }

    @Override
    @Transactional
    public LaundryRoom deactivateRoom(int roomId) {
        LaundryRoom room = getRoomOrThrow(roomId);
        room.deactivateRoom();
        return ILaundryRoomRepository.save(room);
    }

    @Override
    @Transactional
    public LaundryRoom updateRoom(int roomId, String location, int capacity, String description) {
        LaundryRoom room = getRoomOrThrow(roomId);
        room.updateRoom(location, capacity, description);
        return ILaundryRoomRepository.save(room);
    }

    @Override
    @Transactional
    public LaundryRoom addMachineToRoom(int roomId, Machine machine) {
        LaundryRoom room = getRoomOrThrow(roomId);
        room.addMachine(machine);
        return ILaundryRoomRepository.save(room);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LaundryRoom> getRoomById(int roomId) {
        return ILaundryRoomRepository.findById(roomId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LaundryRoom> getActiveRooms() {
        return ILaundryRoomRepository.findByIsActive(true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LaundryRoom> getAllRooms() {
        return ILaundryRoomRepository.findAll();
    }

    private LaundryRoom getRoomOrThrow(int roomId) {
        return ILaundryRoomRepository.findById(roomId)
                .orElseThrow(() -> new NoSuchElementException("LaundryRoom not found with id: " + roomId));
    }


}
