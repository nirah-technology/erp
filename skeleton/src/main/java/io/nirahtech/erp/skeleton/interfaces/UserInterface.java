package io.nirahtech.erp.skeleton.interfaces;

import java.util.Collection;
import java.util.Properties;

import io.nirahtech.erp.plugins.Plugin;

public interface UserInterface {
    void run(Properties configuration);
    void run(Collection<Plugin> plugins, Properties configuration);
    void run(Collection<Plugin> plugins);
}
