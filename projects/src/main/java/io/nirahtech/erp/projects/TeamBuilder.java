package io.nirahtech.erp.projects;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TeamBuilder implements Builder<Team> {
    private UUID id = UUID.randomUUID();
    private String name = null;
    private String description = null;
    private ProjectMember manager = null;
    private final Set<ProjectMember> members = new HashSet<>();


    public TeamBuilder() {

    }

    public final TeamBuilder id(final UUID id) {
        this.id = id;
        return this;
    }

    public final TeamBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public final TeamBuilder description(final String description) {
        this.description = description;
        return this;
    }


    public final TeamBuilder manager(final ProjectMember manager) {
        this.manager = manager;
        return this;
    }
    public final TeamBuilder members(final ProjectMember... members) {
        this.members.addAll(Set.of(members));
        return this;
    }

    @Override
    public Team build() {
        Team team = new Team(new TeamIdentifier(id), name, description, manager, members);
        return team;
    }
    
}
