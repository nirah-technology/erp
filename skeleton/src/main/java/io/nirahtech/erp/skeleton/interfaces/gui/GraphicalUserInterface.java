package io.nirahtech.erp.skeleton.interfaces.gui;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.swing.JFrame;

import io.nirahtech.erp.plugins.Plugin;
import io.nirahtech.erp.skeleton.interfaces.UserInterface;

public final class GraphicalUserInterface implements UserInterface {

    private static UserInterface instance;
    
    public static final UserInterface getInstance() {
        if (Objects.isNull(GraphicalUserInterface.instance)) {
            GraphicalUserInterface.instance = new GraphicalUserInterface();
        } 
        return GraphicalUserInterface.instance;
    }

    private GraphicalUserInterface() { }

    @Override
    public void run(Properties configuration) {
        this.run(List.of(), configuration);
    }

    @Override
    public void run(Collection<Plugin> plugins) {
        this.run(plugins, new Properties());
    }
    
    @Override
    public void run(Collection<Plugin> plugins, Properties configuration) {
        JFrame jframe = new ErpWindow(plugins, configuration);
        jframe.setVisible(true);
    }
    
}
