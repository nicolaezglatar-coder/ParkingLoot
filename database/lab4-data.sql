-- Run after deploying the application so JPA has created the tables.
-- Column names match the entities in this project. The lab's sample uses
-- LICENSEPLATE, PARKINGSPOT, and OWNER_ID, which are not columns here.
USE parkinglot;

INSERT INTO users (ID, EMAIL, PASSWORD, USERNAME)
VALUES (-1, 'admin@admin.com', NULL, 'admin') AS new
ON DUPLICATE KEY UPDATE EMAIL = new.EMAIL, USERNAME = new.USERNAME;

INSERT INTO cars (ID, license_plate, parking_spot, owner)
VALUES (-1, 'SB01ABC', 'Spot 1', -1),
       (-2, 'SB02ABC', 'Spot 2', -1) AS new
ON DUPLICATE KEY UPDATE license_plate = new.license_plate,
                        parking_spot = new.parking_spot,
                        owner = new.owner;
