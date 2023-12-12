package io.nirahtech.erp.projects;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class WorkPackageBuilder implements Builder<WorkPackage> {
    private UUID id = UUID.randomUUID();
    private String name = null;
    private String description = null;
    private ProjectMember referent = null;
    private LocalDate startDate = LocalDate.now();
    private Duration duration = Duration.ofDays(1);
    private Status status = Status.IDLE;
    private Set<File> documentations = new HashSet<>();
    private Set<String> labels = new HashSet<>();
    private Set<Task> tasks = new HashSet<>();
    private Set<Milestone> milestones = new HashSet<>();

    public WorkPackageBuilder() {

    }

    public final WorkPackageBuilder id(final UUID id) {
        this.id = id;
        return this;
    }

    public final WorkPackageBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public final WorkPackageBuilder description(final String description) {
        this.description = description;
        return this;
    }

    public final WorkPackageBuilder startDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public final WorkPackageBuilder duration(final Duration duration) {
        this.duration = duration;
        return this;
    }

    public final WorkPackageBuilder referent(final ProjectMember referent) {
        this.referent = referent;
        return this;
    }
    public final WorkPackageBuilder status(final Status status) {
        this.status = status;
        return this;
    }
    public final WorkPackageBuilder documentations(final File... documentations) {
        this.documentations.addAll(Arrays.asList(documentations));
        return this;
    }
    public final WorkPackageBuilder labels(final String... labels) {
        this.labels.addAll(Arrays.asList(labels));
        return this;
    }
    public final WorkPackageBuilder tasks(final Task... tasks) {
        this.tasks.addAll(Arrays.asList(tasks));
        return this;
    }
    public final WorkPackageBuilder milestones(final Milestone... milestones) {
        this.milestones.addAll(Arrays.asList(milestones));
        return this;
    }

    @Override
    public WorkPackage build() {
        WorkPackage workPackage = new WorkPackage(new WorkPackageIdentifier(id), name, description, referent, startDate, duration, status, documentations, labels, this.tasks, this.milestones);
        return workPackage;
    }

}
