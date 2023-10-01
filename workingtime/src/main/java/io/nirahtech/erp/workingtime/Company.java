package io.nirahtech.erp.workingtime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Company implements Entity, Cashable, Employer {

    private final String name;

    private final ProjectsRegistry projectsRegistry = ProjectsRegistry.getInstance();

    private final Set<Employee> employees = new HashSet<>();

    public Company(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public final Project launchProject(final String name) {
        return this.projectsRegistry.createProject(name);
    }

    public final void abandon(final Project project) {
        this.projectsRegistry.unregister(project);
    }

    public final Collection<Project> getProjects() {
        return Collections.unmodifiableCollection(this.projectsRegistry.getProjects());
    }

    @Override
    public BigDecimal computeGeneratedMoney() {
        final int generatedMoney = this.employees
            .stream()
            .map(Employee::computeGeneratedMoney)
            .mapToInt(BigDecimal::intValue)
            .sum();
        return BigDecimal.valueOf(generatedMoney);
    }

    @Override
    public BigDecimal computeGeneratedMoney(Project project) {
        final int generatedMoney = this.employees
            .stream()
            .map(employee -> employee.computeGeneratedMoney(project))
            .mapToInt(BigDecimal::intValue)
            .sum();
        return BigDecimal.valueOf(generatedMoney);
    }

    @Override
    public BigDecimal computeGeneratedMoney(LocalDate from, LocalDate to) {
        final int generatedMoney = this.employees
            .stream()
            .map(employee -> employee.computeGeneratedMoney(from, to))
            .mapToInt(BigDecimal::intValue)
            .sum();
        return BigDecimal.valueOf(generatedMoney);
    }

    @Override
    public BigDecimal computeGeneratedMoney(LocalDate from, LocalDate to, Project project) {
        final int generatedMoney = this.employees
            .stream()
            .map(employee -> employee.computeGeneratedMoney(from, to, project))
            .mapToInt(BigDecimal::intValue)
            .sum();
        return BigDecimal.valueOf(generatedMoney);
    }

    @Override
    public void recruit(Employee employee) {
        if (Objects.nonNull(employee)) {
            this.employees.add(employee);
        }
    }

    @Override
    public void fire(Employee employee) {
        if (Objects.nonNull(employee) && this.employees.contains(employee)) {
            this.employees.remove(employee);
        }
    }

    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(this.employees);
    }

}
