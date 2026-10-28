-- Run once with a MySQL administrator account (for example, in MySQL Workbench).
-- These are the local tutorial credentials. Change the password for any shared server.
CREATE DATABASE IF NOT EXISTS parkinglot
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'ParkingLotUser'@'localhost'
    IDENTIFIED BY 'ParkingLotPassword';

GRANT ALL PRIVILEGES ON parkinglot.*
    TO 'ParkingLotUser'@'localhost';
