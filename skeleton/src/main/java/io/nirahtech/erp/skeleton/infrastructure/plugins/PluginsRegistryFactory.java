package io.nirahtech.erp.skeleton.infrastructure.plugins;

public final class PluginsRegistryFactory {
    
    private PluginsRegistryFactory() { }

    public static final PluginsRegistry create() {
        return new DefaultPlginRegistry();
    }
}
