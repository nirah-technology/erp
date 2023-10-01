package io.nirahtech.erp.core;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Company implements Entity {
    private final String name;
    private final LocalDate creationDate;
    private final Set<Employee> employees = new HashSet<>();
    private final Set<Project> projects = new HashSet<>();

    public Company(final String name, final LocalDate creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public final String getName() {
        return this.name;
    }
    public final LocalDate getCreationDate() {
        return this.creationDate;
    }
}
