package io.nirahtech.erp.projects;

import java.io.File;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class Milestone implements Serializable {
	private final MilestoneIdentifier id;
	private final String name;
	private Duration duration;
	private String description = null;
	private ProjectMember referent = null;
	private LocalDate startDate = LocalDate.now();
	private Status status = Status.IDLE;
	private final Set<File> documentations = new HashSet<>();
	private final Set<String> labels = new HashSet<>();
	private final Map<Status, Runnable> eventListeners = new HashMap<>();

	public Milestone(
			final MilestoneIdentifier id,
			final String name,
			final String description,
			final ProjectMember referent,
			final LocalDate startDate,
			final Duration duration,
			final Status status,
			final Set<File> documentations,
			final Set<String> labels) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.referent = referent;
		this.startDate = startDate;
		this.duration = duration;
		this.status = status;
		this.documentations.addAll(documentations);
		this.labels.addAll(labels);
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
	public Collection<File> getDocumentations() {
		return documentations;
	}

	public Duration getDuration() {
		return duration;
	}

	public MilestoneIdentifier getId() {
		return id;
	}

	public Collection<String> getLabels() {
		return labels;
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

	public LocalDate getDeadLine() {
		return this.startDate.plus(this.duration);
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Optional<LocalDate> getEstimatedEndDate() {
		Optional<LocalDate> estimatedEndDate = Optional.empty();
		
		return estimatedEndDate;
	}

	public void addEventListenerOnStatusChanged(final Status status, final Runnable callback) {
		this.eventListeners.put(status, callback);
	}
}
