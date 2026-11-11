package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.ejb.CarsBean;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/** Values and server-side validation shared by the add and edit forms. */
final class CarForm {
    private final String licensePlate;
    private final String parkingSpot;
    private final Long ownerId;
    private final String error;

    private CarForm(String licensePlate, String parkingSpot, Long ownerId, String error) {
        this.licensePlate = licensePlate;
        this.parkingSpot = parkingSpot;
        this.ownerId = ownerId;
        this.error = error;
    }

    static CarForm fromRequest(HttpServletRequest request, List<UserDto> users,
                               CarsBean carsBean, Long excludedCarId) {
        String licensePlate = trimmed(request.getParameter("license_plate"));
        String parkingSpot = trimmed(request.getParameter("parking_spot"));
        Long ownerId = parseId(request.getParameter("owner_id"));

        String error = null;
        if (licensePlate.isEmpty() || parkingSpot.isEmpty() || ownerId == null) {
            error = "Complete all fields and choose an owner.";
        } else if (licensePlate.length() > 100 || parkingSpot.length() > 100) {
            error = "License plate and parking spot must have at most 100 characters.";
        } else if (users.stream().noneMatch(user -> user.getId().equals(ownerId))) {
            error = "Choose an existing owner.";
        } else if (carsBean.isLicensePlateInUse(licensePlate, excludedCarId)) {
            error = "This license plate is already in use.";
        } else if (carsBean.isParkingSpotInUse(parkingSpot, excludedCarId)) {
            error = "This parking spot is already in use.";
        }
        return new CarForm(licensePlate, parkingSpot, ownerId, error);
    }

    static Long parseId(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    private static String trimmed(String value) {
        return value == null ? "" : value.trim();
    }

    void expose(HttpServletRequest request) {
        request.setAttribute("formLicensePlate", licensePlate);
        request.setAttribute("formParkingSpot", parkingSpot);
        request.setAttribute("formOwnerId", ownerId);
        request.setAttribute("formError", error);
    }

    boolean isValid() {
        return error == null;
    }

    String licensePlate() {
        return licensePlate;
    }

    String parkingSpot() {
        return parkingSpot;
    }

    Long ownerId() {
        return ownerId;
    }
}
