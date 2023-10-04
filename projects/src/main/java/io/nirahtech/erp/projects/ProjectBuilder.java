package io.nirahtech.erp.projects;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class ProjectBuilder implements Builder<Project> {
    
		
    private UUID id = UUID.randomUUID();
    private String name = null;
    private String description = null;
    private ProjectMember director = null;
    private LocalDate startDate = LocalDate.now();
    private Duration duration = Duration.ofDays(1);
    private Status status = Status.IDLE;
    private final Set<Team> teams = new HashSet<>();
    private final Set<WorkPackage> packages = new HashSet<>();

    public ProjectBuilder() {

    }

    public final ProjectBuilder id(final UUID id) {
        this.id = id;
        return this;
    }

    public final ProjectBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public final ProjectBuilder description(final String description) {
        this.description = description;
        return this;
    }

    public final ProjectBuilder startDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public final ProjectBuilder duration(final Duration duration) {
        this.duration = duration;
        return this;
    }
    public final ProjectBuilder status(final Status status) {
        this.status = status;
        return this;
    }

    public final ProjectBuilder director(final ProjectMember director) {
        this.director = director;
        return this;
    }
    
    public final ProjectBuilder teams(final Team... teams) {
        this.teams.addAll(Set.of(teams));
        return this;
    }


    public final ProjectBuilder workPackages(final WorkPackage... workPackages) {
        this.packages.addAll(Set.of(workPackages));
        return this;
    }

    @Override
    public final Project build() {
        return new Project(new ProjectIdentifier(id), name, description, director, startDate, duration, this.teams, status, this.packages);
    }


}
