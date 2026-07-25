package com.cput.laundryecommercebookingsystem.domain;

public class LaundryMachine {

    private int machineId;
    private String machineNumber;
    private String type;
    private MachineStatus status;
    private LaundryRoom laundryRoom;


    private LaundryMachine(Builder builder) {
        this.machineId = builder.machineId;
        this.machineNumber = builder.machineNumber;
        this.type = builder.type;
        this.status = builder.status;
        this.laundryRoom = builder.laundryRoom;
    }


    public int getMachineId() {
        return machineId;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public String getType() {
        return type;
    }

    public MachineStatus getStatus() {
        return status;
    }

    public LaundryRoom getLaundryRoom() {
        return laundryRoom;
    }


    public void updateStatus(MachineStatus status) {
        this.status = status;
    }


    public void getMachineDetails() {
        System.out.println("Machine ID: " + machineId);
        System.out.println("Machine Number: " + machineNumber);
        System.out.println("Type: " + type);
        System.out.println("Status: " + status);

        if (laundryRoom != null) {
            System.out.println("Laundry Room ID: "
                    + laundryRoom.getRoomId());
        }
    }

    // Builder Pattern
    public static class Builder {

        private int machineId;
        private String machineNumber;
        private String type;
        private MachineStatus status;
        private LaundryRoom laundryRoom;

        public Builder(int machineId, String machineNumber) {
            this.machineId = machineId;
            this.machineNumber = machineNumber;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder status(MachineStatus status) {
            this.status = status;
            return this;
        }

        public Builder laundryRoom(LaundryRoom laundryRoom) {
            this.laundryRoom = laundryRoom;
            return this;
        }

        public LaundryMachine build() {
            return new LaundryMachine(this);
        }
    }
}
}
