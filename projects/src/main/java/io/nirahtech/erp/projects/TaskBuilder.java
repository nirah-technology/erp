package io.nirahtech.erp.projects;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class TaskBuilder implements Builder<Task> {
    private UUID id = UUID.randomUUID();
    private String name = null;
    private String details = null;
    private Status status = Status.IDLE;
    private Task parent = null;
    private Set<Task> subTasks = new HashSet<>();
    private Set<Task> requiredTasks = new HashSet<>();
    private LocalDate startDate = null;
    private Duration duration = Duration.ofDays(1);

    private Priority priority = Priority.NORMAL;
    private Set<String> labels = new HashSet<>();
    private Set<File> documentations = new HashSet<>();
    private ProjectMember referent = null;
    private Set<ProjectMember> workers = new HashSet<>();

    private int maxWorkers = 1;

    TaskBuilder() {
        
    }

    public final TaskBuilder id(final UUID id) {
        this.id = id;
        return this;
    }

    public final TaskBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public final TaskBuilder status(final Status status) {
        this.status = status;
        return this;
    }

    public final TaskBuilder details(final String details) {
        this.details = details;
        return this;
    }

    public final TaskBuilder parent(final Task parent) {
        this.parent = parent;
        return this;
    }

    public final TaskBuilder subTasks(final Task... subTasks) {
        this.subTasks.addAll(Set.of(subTasks));
        return this;
    }
    public final TaskBuilder requiredTasks(final Task... requiredTasks) {
        this.requiredTasks.addAll(Set.of(requiredTasks));
        return this;
    }

    public final TaskBuilder startDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public final TaskBuilder duration(final Duration duration) {
        this.duration = duration;
        return this;
    }

    public final TaskBuilder priority(final Priority priority) {
        this.priority = priority;
        return this;
    }
    public final TaskBuilder referent(final ProjectMember referent) {
        this.referent = referent;
        return this;
    }
    public final TaskBuilder labels(final String... labels) {
        this.labels.addAll(Set.of(labels));
        return this;
    }
    public final TaskBuilder documentations(final File... documentations) {
        this.documentations.addAll(Set.of(documentations));
        return this;
    }
    public final TaskBuilder workers(final ProjectMember... workers) {
        this.workers.addAll(Set.of(workers));
        return this;
    }

    public final TaskBuilder maxWorkers(final int maxWorkers) {
        this.maxWorkers = maxWorkers;
        return this;
    }

    @Override
    public final Task build() {
        final Task task = new Task(new TaskIdentifier(this.id), this.name, this.details, this.status, this.parent, this.subTasks, this.requiredTasks, this.startDate, this.duration, this.priority, this.labels, this.documentations, this.referent, this.workers, this.maxWorkers);
        if (Objects.nonNull(this.parent)) {
            this.parent.getSubTasks().add(task);
        }
        return task;
    }

}
