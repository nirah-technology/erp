package io.nirahtech.erp.skeleton.infrastructure.plugins;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import io.nirahtech.erp.plugins.Plugin;

final class DefaultPlginRegistry implements PluginsRegistry {

    private final Collection<JarFile> retrievePluginsJarFiles(Collection<File> files) {
        final Set<JarFile> jarFiles = new HashSet<>(files.stream()
            .map(file -> {
                JarFile jarFile = null;
                try {
                    jarFile = new JarFile(file);
                } catch (Exception e) {
                    // Ignore the exception
                }
                return jarFile;
            })
            .filter(jarFile -> Objects.nonNull(jarFile))
            .toList());
        return Collections.unmodifiableCollection(jarFiles);
    }

    private final Collection<File> retrievePotentialsPluginsFiles(final File pluginsFolder) {
        final Set<File> potentialsPluginsFiles = new HashSet<>();
        if (Objects.nonNull(pluginsFolder)) {
            try {
                potentialsPluginsFiles.addAll(Files.list(pluginsFolder.toPath())
                        .map(file -> file.toFile())
                        .filter(file -> file.isFile())
                        .filter(file -> file.getName().endsWith(".jar"))
                        .filter(file -> Objects.nonNull(file))
                        .toList());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return Collections.unmodifiableCollection(potentialsPluginsFiles);
    }

    private Collection<JarEntry> extractClassesFromJarFile(final JarFile jarFile) {
        final Set<JarEntry> classesInJarFile = new HashSet<>();
        jarFile.entries().asIterator().forEachRemaining(jarEntry -> {
            if (jarEntry.getName().endsWith(".class")) {
                classesInJarFile.add(jarEntry);
            }
        });
        return Collections.unmodifiableCollection(classesInJarFile);
    }

    private final Class<? extends Plugin> extractPluginClass(final JarFile jarFile) {
        Class<? extends Plugin> pluginClass = null;
        final Collection<JarEntry> classesFromJarFile = this.extractClassesFromJarFile(jarFile);
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        final Set<Class<?>> loadedPluginClassesFromJarFile = new HashSet<>();
        classesFromJarFile.forEach((classFromJarFile) -> {
            final String className = classFromJarFile.getName().replace("/", ".").replace(".class", "");
            try {
                final Class<?> loadedClass = classLoader.loadClass(className);
                if (loadedClass != Plugin.class) {
                    if (Plugin.class.isAssignableFrom(loadedClass)) {
                        loadedPluginClassesFromJarFile.add(loadedClass);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        pluginClass = (Class<? extends Plugin>) loadedPluginClassesFromJarFile.stream().findFirst().orElse(null);
        return pluginClass;
    }

    private final Collection<Class<? extends Plugin>> extractPluginsClasses(final Collection<JarFile> jarFiles) {
        return Collections.unmodifiableCollection(jarFiles.stream().map(jarFile -> this.extractPluginClass(jarFile)).filter(jarFile -> Objects.nonNull(jarFile)).toList());
    }

    private final Plugin loadPlugin(Class<? extends Plugin> pluginClass) {
        Plugin plugin = null;
        try {
            Constructor<? extends Plugin> constructor = pluginClass.getDeclaredConstructor();
            plugin = constructor.newInstance();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return plugin;
    }

    private final Collection<Plugin> loadPlugins(final Collection<Class<? extends Plugin>> pluginsClasses) {
        return Collections.unmodifiableCollection(pluginsClasses.stream().map(pluginClass -> this.loadPlugin(pluginClass)).filter(plugin -> Objects.nonNull(plugin)).toList());
    }

    private final void createIfNotExists(File pluginsFolder) {
        if (!pluginsFolder.exists()) {
            pluginsFolder.mkdirs();
        }
    }


    @Override
    public Collection<Plugin> load(File pluginsFolder) {
        this.createIfNotExists(pluginsFolder);
        final Collection<File> files = this.retrievePotentialsPluginsFiles(pluginsFolder);
        final Collection<JarFile> jarFiles = this.retrievePluginsJarFiles(files);
        final Collection<Class<? extends Plugin>> pluginsClasses = this.extractPluginsClasses(jarFiles);
        final Collection<Plugin> plugins = this.loadPlugins(pluginsClasses);
        System.out.println(plugins.size());
        return plugins;
    }
    
}
