package io.nirahtech.erp.projects;

import java.io.File;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Task implements Serializable {

    private final TaskIdentifier id;
    private final String name;
    private final Task parent;
    private String details;
    private Status status;
    private LocalDate startDate;
    private Duration duration;
    private Priority prority;
    private ProjectMember referent;
    private int maxWorkers;
    private final Set<Task> subTasks = new HashSet();
    private final Set<Task> requiredTasks = new HashSet<>();
    private final Set<File> documentations = new HashSet<>();
    private final Set<String> labels = new HashSet<>();
    private final Set<ProjectMember> workers = new HashSet<>();
    private final Map<Status, Runnable> eventListeners = new HashMap<>();

    public Task(
            TaskIdentifier id,
            String name,
            String details,
            Status status,
            Task parent,
            Set<Task> subTasks,
            Set<Task> requiredTasks,
            LocalDate startDate,
            Duration duration,
            Priority prority,
            Set<String> labels,
            Set<File> documentations,
            ProjectMember referent,
            Set<ProjectMember> workers,
            int maxWorkers) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.status = status;
        this.parent = parent;
        this.subTasks.addAll(subTasks);
        this.requiredTasks.addAll(requiredTasks);
        this.startDate = startDate;
        this.duration = duration;
        this.prority = prority;
        this.labels.addAll(labels);
        this.documentations.addAll(documentations);
        this.referent = referent;
        this.workers.addAll(workers);
        this.maxWorkers = maxWorkers;
    }

    public String getDetails() {
        return details;
    }

    public Set<File> getDocumentations() {
        return documentations;
    }

    public Duration getDuration() {
        return duration;
    }

    public TaskIdentifier getId() {
        return id;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public String getName() {
        return name;
    }

    public Task getParent() {
        return parent;
    }

    public Priority getPrority() {
        return prority;
    }

    public ProjectMember getReferent() {
        return referent;
    }

    public Set<Task> getRequiredTasks() {
        return requiredTasks;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Status getStatus() {
        return status;
    }

    public Set<Task> getSubTasks() {
        return subTasks;
    }

    public Set<ProjectMember> getWorkers() {
        return workers;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setMaxWorkers(int maxWorkers) {
        this.maxWorkers = maxWorkers;
    }

    public void setPrority(Priority prority) {
        this.prority = prority;
    }

    public void setReferent(ProjectMember referent) {
        this.referent = referent;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStatus(Status status) {
		this.status = status;
		if (this.eventListeners.containsKey(status)) {
			Runnable callback = this.eventListeners.get(status);
			callback.run();
		}
    }

    public final LocalDate getDeadLine() {
        return this.startDate.plusDays(this.duration.toDays());
    }

    public void addEventListenerOnStatusChanged(final Status status, final Runnable callback) {
        this.eventListeners.put(status, callback);
    }
}
