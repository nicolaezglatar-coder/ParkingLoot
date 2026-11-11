package com.parking.parkinglot.common;

public class CarDto {
    private final Long id;
    private final String licensePlate;
    private final String parkingSpot;
    private final Long ownerId;
    private final String ownerName;

    public CarDto(Long id, String licensePlate, String parkingSpot, Long ownerId, String ownerName) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.parkingSpot = parkingSpot;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
