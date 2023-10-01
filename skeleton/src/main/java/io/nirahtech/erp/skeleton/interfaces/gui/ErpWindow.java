package io.nirahtech.erp.skeleton.interfaces.gui;

import java.util.Collection;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import io.nirahtech.erp.plugins.Plugin;

public class ErpWindow extends JFrame {

    private final Collection<Plugin> plugins;
    private final Properties configuration;
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu pluginMenu = new JMenu("Plugins");

    public ErpWindow(Collection<Plugin> plugins, Properties configuration) {
        super.setTitle("ERP");
        this.plugins = plugins;
        this.configuration = configuration;

        plugins.forEach(plugin -> {
            final JMenuItem pluginMenuItem = new JMenuItem(plugin.getMenuTitle());
            pluginMenuItem.setMnemonic( 'N' );
            this.pluginMenu.add(pluginMenuItem);
        });
        this.menuBar.add(this.pluginMenu);
        this.setJMenuBar(this.menuBar);
    }
}
