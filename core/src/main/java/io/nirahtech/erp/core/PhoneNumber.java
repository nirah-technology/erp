package io.nirahtech.erp.core;

public final class PhoneNumber {
    private final int countryCode;
    private final int number;

    public PhoneNumber(int countryCode, int number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public int getNumber() {
        return number;
    }

}
