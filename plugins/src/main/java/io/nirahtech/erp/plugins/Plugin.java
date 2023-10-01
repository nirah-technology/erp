package io.nirahtech.erp.plugins;

import java.lang.Runtime.Version;

import javax.swing.JPanel;

public interface Plugin {
    String getName();
    Version getVersion();
    String getMenuTitle();
    JPanel getView();
    
    void setup();
    void execute();
    void shutdown();
}
