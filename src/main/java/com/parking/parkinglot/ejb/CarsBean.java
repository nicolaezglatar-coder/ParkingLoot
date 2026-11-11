package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public List<CarDto> findAllCars() {
        LOG.info("findAllCars");
        try {
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public CarDto findById(Long carId) {
        Car car = entityManager.find(Car.class, carId);
        return car == null ? null : toDto(car);
    }

    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        User owner = requireUser(userId);
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        car.setOwner(owner);
        owner.getCars().add(car);
        entityManager.persist(car);
    }

    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        Car car = entityManager.find(Car.class, carId);
        if (car == null) {
            throw new IllegalArgumentException("Car not found");
        }
        User newOwner = requireUser(userId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        User oldOwner = car.getOwner();
        if (!oldOwner.getId().equals(newOwner.getId())) {
            oldOwner.getCars().remove(car);
            newOwner.getCars().add(car);
            car.setOwner(newOwner);
        }
    }

    public void deleteCarsByIds(Collection<Long> carIds) {
        for (Long carId : carIds) {
            Car car = entityManager.find(Car.class, carId);
            if (car != null) {
                entityManager.remove(car);
            }
        }
    }

    public boolean isLicensePlateInUse(String licensePlate, Long excludedCarId) {
        return isValueInUse("licensePlate", licensePlate, excludedCarId);
    }

    public boolean isParkingSpotInUse(String parkingSpot, Long excludedCarId) {
        return isValueInUse("parkingSpot", parkingSpot, excludedCarId);
    }

    private boolean isValueInUse(String field, String value, Long excludedCarId) {
        String jpql = "SELECT COUNT(c) FROM Car c WHERE c." + field + " = :value";
        if (excludedCarId != null) {
            jpql += " AND c.id <> :excludedCarId";
        }
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class)
                .setParameter("value", value);
        if (excludedCarId != null) {
            query.setParameter("excludedCarId", excludedCarId);
        }
        return query.getSingleResult() > 0;
    }

    private User requireUser(Long userId) {
        User user = entityManager.find(User.class, userId);
        if (user == null) {
            throw new IllegalArgumentException("Owner not found");
        }
        return user;
    }

    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> result = new ArrayList<>(cars.size());
        for (Car car : cars) {
            result.add(toDto(car));
        }
        return result;
    }

    private CarDto toDto(Car car) {
        User owner = car.getOwner();
        return new CarDto(car.getId(), car.getLicensePlate(), car.getParkingSpot(),
                owner.getId(), owner.getUsername());
    }
}
