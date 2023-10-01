package io.nirahtech.erp.workingtime;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public final class ProjectsRegistry {

    private static final ProjectsRegistry INSTANCE = new ProjectsRegistry();

    public static ProjectsRegistry getInstance() {
        return INSTANCE;
    }

    private Set<Project> projects = new HashSet<>();

    public final void register(final Project project) {
        this.projects.add(project);
    }

    public final Project createProject(final String name) {
        final Project project = new Project(name);
        this.register(project);
        return project;
    }

    public final Optional<Project> findByName(final String name) {
        return this.projects.stream().filter(project -> project.name().equalsIgnoreCase(name)).findFirst();
    }
    
    public final void unregister(final Project project) {
        if (Objects.nonNull(project) && this.projects.contains(project)) {
            this.projects.remove(project);
        }
    }

    public final Collection<Project> getProjects() {
        return Collections.unmodifiableCollection(this.projects);
    }

}
