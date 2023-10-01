package io.nirahtech.erp.billing;

public abstract class AbstractOffer implements Offer {

    private final String name;
    private final String description;

    protected AbstractOffer(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }
}
