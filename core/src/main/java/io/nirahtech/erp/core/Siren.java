package io.nirahtech.erp.core;

public final record Siren (
    int code
) {
    private static final int CODE_LENGHT = 9;

    public static final Siren create(final int code) throws IllegalArgumentException {
        if (String.valueOf(code).length() != CODE_LENGHT) {
            throw new IllegalArgumentException(String.format("SIREN must be a number with %s characters.", CODE_LENGHT));
        }
        return new Siren(code);
    }
}
