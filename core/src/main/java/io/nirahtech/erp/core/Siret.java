package io.nirahtech.erp.core;

public final record Siret (
    long code
) {
    private static final int CODE_LENGHT = 14;

    public static final Siret create(final int code) throws IllegalArgumentException {
        if (String.valueOf(code).length() != CODE_LENGHT) {
            throw new IllegalArgumentException(String.format("SIRET must be a number with %s characters.", CODE_LENGHT));
        }
        return new Siret(code);
    }
}