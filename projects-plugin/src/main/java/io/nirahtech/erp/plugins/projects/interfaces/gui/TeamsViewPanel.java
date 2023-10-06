package io.nirahtech.erp.plugins.projects.interfaces.gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import io.nirahtech.erp.plugins.projects.interfaces.gui.table.TeamTableModel;
import io.nirahtech.erp.projects.Team;

public class TeamsViewPanel extends JPanel {

    private final Set<Team> projects;

    
    public TeamsViewPanel(Set<Team> projects) {
        super(new BorderLayout());
        this.projects = projects;

        final JPanel header = this.createHeader();
        this.add(header, BorderLayout.NORTH);

        final JPanel main = this.createMain();
        this.add(main, BorderLayout.CENTER);

        final JPanel footer = this.createFooter();
        this.add(footer, BorderLayout.SOUTH);
    }
    
    private final JPanel createHeader() {
        final JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Existing Teams");
        header.add(title, BorderLayout.CENTER);
        return header;
    }

    private final JPanel createMain() {
        final JPanel main = new JPanel(new BorderLayout());

        final JTable projectsTable = new JTable(new TeamTableModel(this.projects.stream().toList()));
        projectsTable.setFillsViewportHeight(true);
        final JScrollPane scrollPaneForTable = new JScrollPane(projectsTable);
        main.add(scrollPaneForTable, BorderLayout.CENTER);



        return main;
    }

    private final JPanel createFooter() {
        final JPanel footer = new JPanel(new BorderLayout());
        final JButton createNewTeamButton = new JButton("Create a new Team");
        createNewTeamButton.addActionListener((event) -> {
            this.setEnabled(false);
            JFrame parent = (JFrame) SwingUtilities.windowForComponent(this);
            parent.setEnabled(false);
            JFrame dialog = new JFrame();
            dialog.setTitle("ok");
            dialog.setSize(400, 400);
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    parent.setEnabled(true);
                }
            });
            

        });
        footer.add(createNewTeamButton, BorderLayout.SOUTH);
        return footer;
    }

}
