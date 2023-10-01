package io.nirahtech.erp.skeleton.infrastructure.plugins;

import java.io.File;
import java.util.Collection;

import io.nirahtech.erp.plugins.Plugin;

public interface PluginsRegistry {
    Collection<Plugin> load(final File pluginsFolder);
}
