package io.nirahtech.erp.skeleton.interfaces.gui;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.plugins.Plugin;
import io.nirahtech.erp.plugins.Version;

public class ErpWindow extends JFrame {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private final Collection<Plugin> plugins;
    private final Properties configuration;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu pluginMenu = new JMenu("Plugins");

    private final String retrieveVersion() {
        StringBuilder builder = new StringBuilder();
        Version realVersion = null;
        final Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("skeleton.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (properties.containsKey("version")) {
            realVersion = Version.parse(properties.getProperty("version"));
        }
        if (realVersion != null) {
            builder
                .append(realVersion.major())
                .append(".")
                .append(realVersion.minor());
            realVersion.security().ifPresent((security) -> {
                builder.append(".")
                .append(security);
            });
            realVersion.tag().ifPresent((tag) -> {
                builder.append("-")
                .append(tag);
            });
        }

        return builder.toString();
    }

    public ErpWindow(Collection<Plugin> plugins, Properties configuration) {
        super.setTitle(String.format("NIRAH-TECHNOLOGY :: Enterprise Resource Planning (v%s)", this.retrieveVersion()));
        this.plugins = plugins;
        this.configuration = configuration;

        plugins.forEach(plugin -> {
            final JMenuItem pluginMenuItem = new JMenuItem(plugin.getMenuTitle());
            pluginMenuItem.setMnemonic( 'N' );
            this.pluginMenu.add(pluginMenuItem);
        });
        
        this.menuBar.add(this.pluginMenu);
        this.setJMenuBar(this.menuBar);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Company company = null;
        this.setContentPane(new DefaultPanel(company));
    }
}
