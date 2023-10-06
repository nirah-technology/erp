package io.nirahtech.erp.skeleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import io.nirahtech.erp.plugins.Plugin;
import io.nirahtech.erp.skeleton.infrastructure.plugins.PluginsRegistry;
import io.nirahtech.erp.skeleton.infrastructure.plugins.PluginsRegistryFactory;
import io.nirahtech.erp.skeleton.interfaces.UserInterface;
import io.nirahtech.erp.skeleton.interfaces.UserInterfaceFactory;

public final class SkeletonApplication {

    private static final String CONFIGURATION_FILE = "skeleton.properties";

    private static final Properties loadDefaultEmbededConfigurationFile() {
        final Properties configuration = new Properties();
        try (InputStream inputStream = SkeletonApplication.class.getResourceAsStream("/"+CONFIGURATION_FILE)) {
            configuration.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }

    public static final Properties overloadConfigurationWithExternalFile(final Properties configuration, final String[] commandLineArguments) {
        if (commandLineArguments.length == 2) {
            if (commandLineArguments[0].toLowerCase().contains("conf")) {
                final File configurationFile = new File(commandLineArguments[1]);
                if (configurationFile.exists() && configurationFile.isFile()) {
                    try (InputStream inputStream = new FileInputStream(configurationFile)) {
                        configuration.load(inputStream);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return configuration;

    }
    
    private static final Properties loadConfiguration(final String[] commandLineArguments) {
        Properties configuration = loadDefaultEmbededConfigurationFile();
        configuration = overloadConfigurationWithExternalFile(configuration, commandLineArguments);
        return configuration;
    }

    public final static void main(final String[] commandLineArguments) {

        // Load configuration
        final Properties configuration = loadConfiguration(commandLineArguments);

        // Load plugins
        final File pluginsFolder = new File(configuration.getProperty(ApplicationConfiguration.PLUGINS_LOCATION));
        System.out.println(pluginsFolder.getAbsolutePath());
        final PluginsRegistry pluginsRegistry = PluginsRegistryFactory.create();
        final Collection<Plugin> plugins = pluginsRegistry.load(pluginsFolder);
        System.out.println(plugins.size());

        // Launch UI
        final UserInterface ui = UserInterfaceFactory.gui();
        ui.run(plugins, configuration);
    }
}
