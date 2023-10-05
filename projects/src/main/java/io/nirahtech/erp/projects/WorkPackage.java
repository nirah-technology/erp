package io.nirahtech.erp.projects;

import java.io.File;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import io.nirahtech.erp.projects.utils.CriticalPathCalculator;

public final class WorkPackage implements Serializable {
	private final WorkPackageIdentifier id;
	private final String name;
	private Duration duration;
	private String description = null;
	private ProjectMember referent = null;
	private LocalDate startDate = LocalDate.now();
	private Status status = Status.IDLE;
	private final Set<File> documentations = new HashSet<>();
	private final Set<String> labels = new HashSet<>();
	private final Set<Task> tasks = new HashSet<>();
	private final Set<Milestone> milestones = new HashSet<>();
	private final Map<Status, Runnable> eventListeners = new HashMap<>();

	public WorkPackage(
			final WorkPackageIdentifier id,
			final String name,
			final String description,
			final ProjectMember referent,
			final LocalDate startDate,
			final Duration duration,
			final Status status,
			final Set<File> documentations,
			final Set<String> labels,
			final Set<Task> tasks,
			final Set<Milestone> milestones) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.referent = referent;
		this.startDate = startDate;
		this.duration = duration;
		this.status = status;
		this.documentations.addAll(documentations);
		this.labels.addAll(labels);
		this.tasks.addAll(tasks);
		this.milestones.addAll(milestones);
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public void addTask(final Task task) {
		this.tasks.add(task);
	}
	public void addTasks(final Task... tasks) {
		this.tasks.addAll(Set.of(tasks));
	}
	public void addLabel(final String label) {
		this.labels.add(label);
	}
	public void addLabels(final String... labels) {
		this.labels.addAll(Set.of(labels));
	}
	public void addDocumentation(final File documentation) {
		this.documentations.add(documentation);
	}
	public void addDocumentations(final File... documentations) {
		this.documentations.addAll(Set.of(documentations));
	}
	
	public void addMilestone(final Milestone milestone) {
		this.milestones.add(milestone);
	}
	public void addMilestones(final Milestone... milestones) {
		this.milestones.addAll(Set.of(milestones));
	}
	public Collection<File> getDocumentations() {
		return documentations;
	}

	public Duration getDuration() {
		return duration;
	}

	public WorkPackageIdentifier getId() {
		return id;
	}

	public Collection<String> getLabels() {
		return labels;
	}

	public Collection<Milestone> getMilestones() {
		return milestones;
	}

	public String getName() {
		return name;
	}

	public ProjectMember getReferent() {
		return referent;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public Status getStatus() {
		return status;
	}

	public Collection<Task> getTasks() {
		return tasks;
	}

	public LocalDate getDeadLine() {
		return this.startDate.plus(this.duration);
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Optional<LocalDate> getEstimatedEndDate() {
		Optional<LocalDate> estimatedEndDate = Optional.empty();
		List<Task> list = this.tasks.stream().toList();
		if (!list.isEmpty()) {
			Task mostFutureTask = list.get(0);

			for (Task task : list) {
				if (task.getDeadLine().isAfter(mostFutureTask.getDeadLine())) {
					mostFutureTask = task;
				}
			}

			estimatedEndDate = Optional.ofNullable(mostFutureTask.getDeadLine());
		}
		return estimatedEndDate;
	}

	public final long computeCumulativeWorkingDays() {
		return tasks.stream()
				.mapToLong(task -> task.getDuration().toDays())
				.sum();
	}

	public final long computeMaxCriticalPathValue() {
		return CriticalPathCalculator.compute(this);
	}

	public void addEventListenerOnStatusChanged(final Status status, final Runnable callback) {
		this.eventListeners.put(status, callback);
	}
}
