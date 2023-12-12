package io.nirahtech.erp.projects;

import java.util.Set;

public class Team {
    private final TeamIdentifier id;
    private final String name;
    private final String description;
    private final ProjectMember manager;
    private final Set<ProjectMember> members;

    public Team(TeamIdentifier id, String name, String description, ProjectMember manager, Set<ProjectMember> members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.members = members;
    }

    public TeamIdentifier getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProjectMember getManager() {
        return manager;
    }

    public Set<ProjectMember> getMembers() {
        return members;
    }
}
