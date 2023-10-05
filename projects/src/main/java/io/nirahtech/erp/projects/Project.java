package io.nirahtech.erp.projects;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import io.nirahtech.erp.projects.utils.CriticalPathCalculator;

public final class Project implements Serializable {
  private final ProjectIdentifier id;
  private final String name;
  private String description;
  private ProjectMember director;
  private LocalDate startDate;
  private Status status;
  private Duration duration;
  private final Set<Team> teams;
  private final Set<WorkPackage> workPackages;
	private final Map<Status, Runnable> eventListeners = new HashMap<>();

  public Project(
      ProjectIdentifier id,
      String name,
      String description,
      ProjectMember director,
      LocalDate startDate,
      Duration duration,
      Set<Team> teams,
      Status status,
      Set<WorkPackage> workPackages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.director = director;
        this.startDate = startDate;
        this.duration = duration;
        this.teams = teams;
        this.status = status;
        this.workPackages = workPackages;
  }

  public String getDescription() {
      return description;
  }
  public ProjectMember getDirector() {
      return director;
  }
  public Duration getDuration() {
      return duration;
  }
  public ProjectIdentifier getId() {
      return id;
  }
  public String getName() {
      return name;
  }
  public Set<WorkPackage> getWorkPackages() {
      return workPackages;
  }
  public LocalDate getStartDate() {
      return startDate;
  }
  public Status getStatus() {
      return status;
  }
  public Set<Team> getTeams() {
      return teams;
  }
  public void setDescription(String description) {
      this.description = description;
  }
  public void setDirector(ProjectMember director) {
      this.director = director;
  }
  public void setDuration(Duration duration) {
      this.duration = duration;
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

  public final long computeCumulativeWorkingDays() {
    return workPackages.stream()
        .mapToLong(WorkPackage::computeCumulativeWorkingDays)
        .sum();
  }

  public Optional<LocalDate> getEstimatedEndDate() {
    long timestamp = this.workPackages
        .stream()
        .map(WorkPackage::getEstimatedEndDate)
        .mapToLong(workPackage -> workPackage.get().toEpochDay())
        .max().orElse(0);
    return Optional.ofNullable(LocalDate.ofEpochDay(timestamp));
  }

  public final long computeMaxCriticalPathValue() {
    return CriticalPathCalculator.compute(this);
  }
  
	public void addEventListenerOnStatusChanged(final Status status, final Runnable callback) {
		this.eventListeners.put(status, callback);
	}
}