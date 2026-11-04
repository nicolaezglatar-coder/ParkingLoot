package com.parking.parkinglot.servlets;

import com.parking.parkinglot.ejb.UsersBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "usersServlet", value = "/Users")
public class Users extends HttpServlet {
    @Inject
    private UsersBean usersBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("activePage", "Users");
        request.setAttribute("users", usersBean.findAllUsers());
        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request, response);
    }
}
