package io.nirahtech.erp.webapp.interfaces.web.controllers;

import java.io.IOException;

import io.nirahtech.erp.webapp.services.auth.AuthService;
import io.nirahtech.erp.webapp.services.auth.InMemoryAuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final AuthService authService;

    public LoginServlet() {
        this.authService = new InMemoryAuthService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("OK");
    }
}
