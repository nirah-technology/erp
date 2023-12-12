package io.nirahtech.erp.core;

public final class Siren {
    private final int code;
    private static final int CODE_LENGTH = 9;

    private Siren(int code) {
        this.code = code;
    }

    public static Siren create(int code) throws IllegalArgumentException {
        if (String.valueOf(code).length() != CODE_LENGTH) {
            throw new IllegalArgumentException(String.format("SIREN must be a number with %s characters.", CODE_LENGTH));
        }
        return new Siren(code);
    }

    public int getCode() {
        return code;
    }

}
