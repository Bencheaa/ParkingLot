package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.ejb.CarsBean;
import com.parking.parkinglot.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EditCar", value = "/EditCar")
public class EditCar extends HttpServlet {

    @Inject
    CarsBean carsBean;

    @Inject
    UserBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long carId = parseId(request.getParameter("id"));
        if (carId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid car ID.");
            return;
        }

        CarDto car = carsBean.findById(carId);
        if (car == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Car not found.");
            return;
        }

        request.setAttribute("car", car);
        request.setAttribute("users", usersBean.findAllUsers());
        request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long carId = parseId(request.getParameter("id"));
        Long ownerId = parseId(request.getParameter("owner_id"));
        String licensePlate = request.getParameter("license_plate");
        String parkingSpot = request.getParameter("parking_spot");
        if (carId == null || ownerId == null || licensePlate == null || licensePlate.trim().isEmpty()
                || parkingSpot == null || parkingSpot.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All car details are required.");
            return;
        }

        if (!carsBean.updateCar(carId, licensePlate.trim(), parkingSpot.trim(), ownerId)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Car or owner not found.");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/Cars");
    }

    private Long parseId(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
