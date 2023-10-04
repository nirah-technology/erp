package io.nirahtech.erp.projects;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

public record Project(
    String name,
    String description,
    ProjectMember director,
    Set<Team> teams,
    Set<WorkPackage> packages) {

  public final long computeCumulativeWorkingDays() {
    return packages.stream()
        .mapToLong(WorkPackage::computeCumulativeWorkingDays)
        .sum();
  }

  public Optional<LocalDate> getEstimatedEndDate() {
    long timestamp = this.packages
        .stream()
        .map(WorkPackage::getEstimatedEndDate)
        .mapToLong(workPackage -> workPackage.get().toEpochDay())
        .max().orElse(0);
    return Optional.ofNullable(LocalDate.ofEpochDay(timestamp));
  }

  public final long computeMaxCriticalPathValue() {
    return CriticalPathCalculator.compute(this);
  }
}