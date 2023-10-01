package io.nirahtech.erp.skeleton.interfaces.cli;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import io.nirahtech.erp.plugins.Plugin;
import io.nirahtech.erp.skeleton.interfaces.UserInterface;

public final class CommandLineInterface implements UserInterface {

    private static UserInterface instance;
    
    public static final UserInterface getInstance() {
        if (Objects.isNull(CommandLineInterface.instance)) {
            CommandLineInterface.instance = new CommandLineInterface();
        } 
        return CommandLineInterface.instance;
    }

    private CommandLineInterface() { }

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
        // TODO Auto-generated method stub
        
    }
    
    
}
