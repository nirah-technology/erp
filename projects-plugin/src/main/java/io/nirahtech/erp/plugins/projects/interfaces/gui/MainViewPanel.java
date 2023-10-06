package io.nirahtech.erp.plugins.projects.interfaces.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.nirahtech.erp.projects.Project;
import io.nirahtech.erp.projects.Team;

public class MainViewPanel extends JPanel {
    private static final int BUTTON_SIZE = 150;
    private static final int BUTTON_WIDTH = BUTTON_SIZE;
    private static final int BUTTON_HEIGHT = BUTTON_SIZE;

    private final Set<Project> projects;

    private final JPanel createHeader() {
        final JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Projects  Home");
        header.add(title, BorderLayout.CENTER);
        return header;
    }

    private final JPanel createMain() {
        final JPanel main = new JPanel();
        final JButton teamsManagerButton = new JButton("Gérer les Equipes");
        final JButton projectsManagerButton = new JButton("Gérer les Projects");
        teamsManagerButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        projectsManagerButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        teamsManagerButton.addActionListener((event) -> {
            this.removeAll();
            Set<Team> teams = this.projects.stream().flatMap(project -> project.getTeams().stream().map(team -> team)).collect(Collectors.toSet());
            this.add(new TeamsViewPanel(teams));
            this.revalidate();
        });
        projectsManagerButton.addActionListener((event) -> {
            this.removeAll();
            this.add(new ProjectsViewPanel(this.projects));
            this.revalidate();
        });
        main.add(teamsManagerButton, BorderLayout.CENTER);
        main.add(projectsManagerButton, BorderLayout.CENTER);

        return main;
    }

    private final JPanel createFooter() {
        final JPanel footer = new JPanel(new BorderLayout());
        
        return footer;
    }

    public MainViewPanel(Set<Project> projects) {
        super(new BorderLayout());
        this.projects = projects;

        final JPanel header = this.createHeader();
        this.add(header, BorderLayout.NORTH);

        final JPanel main = this.createMain();
        this.add(main, BorderLayout.CENTER);

        final JPanel footer = this.createFooter();
        this.add(footer, BorderLayout.SOUTH);
    }
}
