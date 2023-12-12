package io.nirahtech.erp.workingtime;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class Imputation {
    private final LocalDate date;
    private final int number;
    private final TimeUnit timeUnit;
    private final Project project;
    private final String details;

    public Imputation(LocalDate date, int number, TimeUnit timeUnit, Project project, String details) {
        this.date = date;
        this.number = number;
        this.timeUnit = timeUnit;
        this.project = project;
        this.details = details;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public Project getProject() {
        return project;
    }

    public String getDetails() {
        return details;
    }
}
