package io.nirahtech.erp.core;

import java.time.LocalDate;

public abstract class Human implements Entity {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final Gender gender;

    protected Human(final String firstName, final String lastName, final LocalDate birthDate, final Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }
    public final String getFirstName() {
        return this.firstName;
    }

    public final String getLastName() {
        return this.lastName;
    }

    public final LocalDate getBirthDate() {
        return this.birthDate;
    }
    public final Gender getGender() {
        return this.gender;
    }
}
