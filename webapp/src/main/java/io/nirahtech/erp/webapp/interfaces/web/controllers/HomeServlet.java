package io.nirahtech.erp.webapp.interfaces.web.controllers;

import java.io.IOException;
import java.util.Optional;

import io.nirahtech.erp.webapp.infrastructure.WebResource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<String> content = WebResource.getInstance().retrieveFile(this.getServletContext().getResourceAsStream(WebResource.RESOURCE_STATIC_FOLDER + "/index.html"));
        if (content.isPresent()) {
            response.getWriter().write(content.get());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
