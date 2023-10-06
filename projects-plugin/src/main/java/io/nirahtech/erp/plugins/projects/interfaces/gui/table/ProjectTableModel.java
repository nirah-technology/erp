package io.nirahtech.erp.plugins.projects.interfaces.gui.table;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

import io.nirahtech.erp.projects.Project;
import io.nirahtech.erp.projects.Status;

public final class ProjectTableModel extends AbstractTableModel {

    private static final Map<String, Class<?>> COLUMNS = new HashMap<>(); 

    private final List<Project> projects;

    public ProjectTableModel(final List<Project> projects) {
        this.projects = projects;
        COLUMNS.put("Name", String.class);
        COLUMNS.put("Starting Date", String.class);
        COLUMNS.put("Dead Line", String.class);
        COLUMNS.put("Status", Status.class);
        COLUMNS.put("Accomplishment (%)", String.class);
        COLUMNS.put("Manager", String.class);
    }

    @Override
    public int getRowCount() {
        return this.projects.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Project project = this.getProject(rowIndex);
        if (Objects.nonNull(project)) {
            switch (columnIndex) {
                case 0:
                    value = project.getName();
                    break;
                case 1:
                    value = project.getStartDate().toString();
                    break;
                case 2:
                    value = LocalDate.now();
                    break;
                case 3:
                    value = project.getStatus();
                    break;
                case 4:
                    value = 0;
                    break;
                case 5:
                    value = project.getDirector().id().value().toString();
                    break;
            
                default:
                    break;
            }
        }
        return value;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMNS.values().stream().toList().get(columnIndex);
    }
    @Override
    public String getColumnName(int column) {
        return COLUMNS.keySet().stream().toList().get(column);
    }

    public final Project getProject(final int rowIndex) {
        Project project = null;
        if (rowIndex >= 0 && rowIndex < this.projects.size()) {
            project = this.projects.get(rowIndex);
        }
        return project;
    }
    
}
