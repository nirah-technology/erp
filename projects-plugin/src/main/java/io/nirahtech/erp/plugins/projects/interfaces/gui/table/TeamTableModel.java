package io.nirahtech.erp.plugins.projects.interfaces.gui.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

import io.nirahtech.erp.projects.Team;

public final class TeamTableModel extends AbstractTableModel {

    private static final Map<String, Class<?>> COLUMNS = new HashMap<>(); 

    private final List<Team> teams;

    public TeamTableModel(final List<Team> teams) {
        this.teams = teams;
        COLUMNS.put("Name", String.class);
        COLUMNS.put("Manager", String.class);
        COLUMNS.put("Total members", Integer.class);
    }

    @Override
    public int getRowCount() {
        return this.teams.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        Team team = this.getTeam(rowIndex);
        if (Objects.nonNull(team)) {
            switch (columnIndex) {
                case 0:
                    value = team.name();
                    break;
                case 1:
                    value = team.manager().id().value().toString();
                    break;
                case 2:
                    value = team.members().size();
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

    public final Team getTeam(final int rowIndex) {
        Team team = null;
        if (rowIndex >= 0 && rowIndex < this.teams.size()) {
            team = this.teams.get(rowIndex);
        }
        return team;
    }
    
}
