package io.nirahtech.erp.webapp.interfaces.web.controllers.api.company;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.webapp.interfaces.web.dto.CreateCompanyRequestDto;
import io.nirahtech.erp.webapp.services.CompanyDirectory;
import io.nirahtech.erp.webapp.services.CompanyDirectoryFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api/companies")
public class CompanyRegistrerServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogManager().getLogger(CompanyRegistrerServlet.class.getName());
    private final CompanyDirectory companyDirectory;
    
    public CompanyRegistrerServlet() {
        this.companyDirectory = CompanyDirectoryFactory.getCompanyDirectory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Gson requestDtoGson = new GsonBuilder().create();
        final CreateCompanyRequestDto companyRequestDto = requestDtoGson.fromJson(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())), CreateCompanyRequestDto.class);
        final Company createdCompany = companyDirectory.create(companyRequestDto.name(), companyRequestDto.siret(), companyRequestDto.siren());
        final Gson responserequestDtoGson = new GsonBuilder().create();
        response.getWriter().println(responserequestDtoGson.toJson(createdCompany));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

}
