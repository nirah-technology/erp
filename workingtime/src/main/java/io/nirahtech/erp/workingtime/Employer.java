package io.nirahtech.erp.workingtime;

import java.util.Collection;

public interface Employer {
    void recruit(Employee employee);
    void fire(Employee employee);
    Collection<Employee> getEmployees();
}
