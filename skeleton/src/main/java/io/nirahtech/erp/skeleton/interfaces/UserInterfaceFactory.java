package io.nirahtech.erp.skeleton.interfaces;

import io.nirahtech.erp.skeleton.interfaces.cli.CommandLineInterface;
import io.nirahtech.erp.skeleton.interfaces.gui.GraphicalUserInterface;

public final class UserInterfaceFactory {
    private UserInterfaceFactory() { }

    public static final UserInterface gui() {
        return GraphicalUserInterface.getInstance();
    }

    public static final UserInterface cli() {
        return CommandLineInterface.getInstance();
    }
}
