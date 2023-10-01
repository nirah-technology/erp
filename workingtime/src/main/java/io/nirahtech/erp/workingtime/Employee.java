package io.nirahtech.erp.workingtime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public final class Employee implements Entity, Imputable, Cashable, Remunerable {

    private final WorkTimesheet workTimesheet;
    private final String firstName;
    private final String lastName;
    private final BigDecimal grossHourlyRate;

    
    public Employee(final String firstName, final String lastName, final BigDecimal grossHourlyRate) {
        this.workTimesheet = new WorkTimesheet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.grossHourlyRate = grossHourlyRate;
    }

    @Override
    public void impute(Imputation workingTime) {
        this.workTimesheet.impute(workingTime);
    }

    @Override
    public int computeWorkedTime(final LocalDate from, final LocalDate to, final TimeUnit timeUnit) {
        return this.workTimesheet.computeWorkedTime(from, to, timeUnit);
    }

    @Override
    public int computeWorkedTime(final TimeUnit timeUnit) {
        return this.workTimesheet.computeWorkedTime(timeUnit);
    }

    @Override
    public Collection<Imputation> listImputations() {
        return Collections.unmodifiableCollection(this.workTimesheet.listImputations());
    }

    public final WorkTimesheet getWorkTimesheet() {
        return this.workTimesheet;
    }

    @Override
    public int computeWorkedTimeOnProject(LocalDate from, LocalDate to, TimeUnit timeUnit, Project project) {
        return this.workTimesheet.computeWorkedTimeOnProject(from, to, timeUnit, project);
    }

    @Override
    public int computeWorkedTimeOnProject(TimeUnit timeUnit, Project project) {
        return this.workTimesheet.computeWorkedTimeOnProject(timeUnit, project);
    }

    public final String getFirstName() {
        return this.firstName;
    }
    public final String getLastName() {
        return this.lastName;
    }
    public final BigDecimal getGrossHourlyRate() {
        return this.grossHourlyRate;
    }

    @Override
    public BigDecimal computeGeneratedMoney() {
        return this.grossHourlyRate.multiply(BigDecimal.valueOf(this.computeWorkedTime(TimeUnit.HOURS)));
    }

    @Override
    public BigDecimal computeGeneratedMoney(Project project) {
        return this.grossHourlyRate.multiply(BigDecimal.valueOf(this.computeWorkedTimeOnProject(TimeUnit.HOURS, project)));
    }

    @Override
    public BigDecimal computeGeneratedMoney(LocalDate from, LocalDate to) {
        return this.grossHourlyRate.multiply(BigDecimal.valueOf(this.computeWorkedTime(from, to, TimeUnit.HOURS)));
    }

    @Override
    public BigDecimal computeGeneratedMoney(LocalDate from, LocalDate to, Project project) {
        return this.grossHourlyRate.multiply(BigDecimal.valueOf(this.computeWorkedTimeOnProject(from, to, TimeUnit.HOURS, project)));
    }
    
}
