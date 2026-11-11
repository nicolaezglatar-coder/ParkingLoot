package com.parking.parkinglot.servlets;

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

@WebServlet(name = "addCarServlet", value = "/AddCar")
public class AddCar extends HttpServlet {
    @Inject
    private UsersBean usersBean;

    @Inject
    private CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showForm(request, response, usersBean.findAllUsers());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<UserDto> users = usersBean.findAllUsers();
        CarForm form = CarForm.fromRequest(request, users, carsBean, null);
        if (!form.isValid()) {
            form.expose(request);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            showForm(request, response, users);
            return;
        }
        carsBean.createCar(form.licensePlate(), form.parkingSpot(), form.ownerId());
        response.sendRedirect(request.getContextPath() + "/Cars");
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, List<UserDto> users)
            throws ServletException, IOException {
        request.setAttribute("activePage", "Cars");
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/addCar.jsp").forward(request, response);
    }
}
