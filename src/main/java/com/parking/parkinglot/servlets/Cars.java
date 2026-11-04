package com.parking.parkinglot.servlets;

import com.parking.parkinglot.ejb.CarsBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "carsServlet", value = "/Cars")
public class Cars extends HttpServlet {
    @Inject
    private CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("activePage", "Cars");
        request.setAttribute("numberOfFreeParkingSpots", 10);
        request.setAttribute("cars", carsBean.findAllCars());
        request.getRequestDispatcher("/WEB-INF/pages/cars.jsp").forward(request, response);
    }
}
