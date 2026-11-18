package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Cars;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars() {
        LOG.info("findAllCars");
        try {
            TypedQuery<Cars> typedQuery = entityManager.createQuery("SELECT c FROM Cars c", Cars.class);
            List<Cars> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<CarDto> copyCarsToDto(List<Cars> cars) {
        List<CarDto> carDtoList = new ArrayList<>();
        for (Cars car : cars) {
            carDtoList.add(copyCarToDto(car));
        }
        return carDtoList;
    }

    private CarDto copyCarToDto(Cars car) {
        return new CarDto(car.getId(), car.getLicensePlate(), car.getParkingSpot(),
                car.getOwner().getUsername(), car.getOwner().getId());
    }

    public CarDto findById(Long carId) {
        LOG.info("findById");
        Cars car = entityManager.find(Cars.class, carId);
        return car == null ? null : copyCarToDto(car);
    }

    public boolean updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");
        Cars car = entityManager.find(Cars.class, carId);
        User owner = entityManager.find(User.class, userId);
        if (car == null || owner == null) {
            return false;
        }

        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        car.setOwner(owner);
        return true;
    }

    public void deleteCarsByIds(List<Long> carIds) {
        LOG.info("deleteCarsByIds");
        for (Long carId : carIds) {
            Cars car = entityManager.find(Cars.class, carId);
            if (car != null) {
                entityManager.remove(car);
            }
        }
    }

    public void createCar(String licensePlate, String parkingSpot, Long userId)
    {
        LOG.info("createCar");
        Cars car = new Cars();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user=entityManager.find(User.class, userId);
        user.getCars().add(car);
        car.setOwner(user);

        entityManager.persist(car);
    }


}

