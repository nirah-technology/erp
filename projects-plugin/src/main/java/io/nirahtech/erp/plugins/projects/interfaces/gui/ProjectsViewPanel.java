package io.nirahtech.erp.plugins.projects.interfaces.gui;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import io.nirahtech.erp.plugins.projects.interfaces.gui.table.ProjectTableModel;
import io.nirahtech.erp.projects.Project;

public class ProjectsViewPanel extends JPanel {

    private final Set<Project> projects;

    private final JPanel createHeader() {
        final JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Existing Projects");
        header.add(title, BorderLayout.CENTER);
        return header;
    }

    private final JPanel createMain() {
        final JPanel main = new JPanel(new BorderLayout());

        final JTable projectsTable = new JTable(new ProjectTableModel(this.projects.stream().toList()));
        projectsTable.setFillsViewportHeight(true);
        final JScrollPane scrollPaneForTable = new JScrollPane(projectsTable);
        main.add(scrollPaneForTable, BorderLayout.CENTER);



        return main;
    }

    private final JPanel createFooter() {
        final JPanel footer = new JPanel(new BorderLayout());
        final JButton createNewProjectButton = new JButton("Create a new Project");
        footer.add(createNewProjectButton, BorderLayout.SOUTH);
        return footer;
    }

    public ProjectsViewPanel(Set<Project> projects) {
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
