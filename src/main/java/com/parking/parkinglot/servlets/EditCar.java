package com.parking.parkinglot.servlets;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.ejb.CarsBean;
import com.parking.parkinglot.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "editCarServlet", value = "/EditCar")
public class EditCar extends HttpServlet {
    @Inject
    private UsersBean usersBean;

    @Inject
    private CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long carId = CarForm.parseId(request.getParameter("id"));
        if (carId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        CarDto car = carsBean.findById(carId);
        if (car == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        request.setAttribute("formLicensePlate", car.getLicensePlate());
        request.setAttribute("formParkingSpot", car.getParkingSpot());
        request.setAttribute("formOwnerId", car.getOwnerId());
        showForm(request, response, carId, usersBean.findAllUsers());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long carId = CarForm.parseId(request.getParameter("car_id"));
        if (carId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (carsBean.findById(carId) == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        List<UserDto> users = usersBean.findAllUsers();
        CarForm form = CarForm.fromRequest(request, users, carsBean, carId);
        if (!form.isValid()) {
            form.expose(request);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            showForm(request, response, carId, users);
            return;
        }
        carsBean.updateCar(carId, form.licensePlate(), form.parkingSpot(), form.ownerId());
        response.sendRedirect(request.getContextPath() + "/Cars");
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, Long carId,
                          List<UserDto> users) throws ServletException, IOException {
        request.setAttribute("activePage", "Cars");
        request.setAttribute("carId", carId);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/editCar.jsp").forward(request, response);
    }
}
