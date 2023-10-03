package io.nirahtech.erp.projects;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public final class TaskBuilder {
    private UUID id = UUID.randomUUID();
    private String name = null;
    private String details = null;
    private Status status = Status.IDLE;
    private Task parent = null;
    private Set<Task> subTasks = new HashSet<>();
    private Set<Task> requiredTasks = new HashSet<>();
    private LocalDate startDate = null;
    private Duration duration = Duration.ofDays(1);
    private int workers = 1;

    TaskBuilder(final String name) {
        this.name = name;
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
        this.requiredTasks.add(parent);
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

    public final TaskBuilder workers(final int workers) {
        this.workers = workers;
        return this;
    }

    public final Task build() {
        final Task task = new Task(this.id, this.name, this.details, this.status, this.parent, this.subTasks, this.requiredTasks, this.startDate, this.duration, this.workers);
        if (Objects.nonNull(this.parent)) {
            this.parent.subTasks().add(task);
        }
        return task;
    }

}
