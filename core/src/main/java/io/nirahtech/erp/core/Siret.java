package io.nirahtech.erp.core;

public final class Siret {
    private final long code;
    private static final int CODE_LENGTH = 14;

    private Siret(long code) {
        this.code = code;
    }

    public static Siret create(long code) throws IllegalArgumentException {
        if (String.valueOf(code).length() != CODE_LENGTH) {
            throw new IllegalArgumentException(String.format("SIRET must be a number with %s characters.", CODE_LENGTH));
        }
        return new Siret(code);
    }

    public long getCode() {
        return code;
    }

}
