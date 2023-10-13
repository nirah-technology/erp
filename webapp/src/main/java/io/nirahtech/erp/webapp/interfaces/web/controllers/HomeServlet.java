package io.nirahtech.erp.webapp.interfaces.web.controllers;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import io.nirahtech.erp.webapp.infrastructure.WebResource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) {
            response.sendRedirect(request.getContextPath() +"/login");
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
