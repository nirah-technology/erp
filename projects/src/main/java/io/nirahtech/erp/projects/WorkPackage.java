package io.nirahtech.erp.projects;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nirahtech.erp.projects.utils.CriticalPathCalculator;

public record WorkPackage(
		WorkPackageIdentifier id,
		String name,
		String description,
		ProjectMember referent,
		LocalDate startDate,
		Duration duration,
		Status status,
		Set<File> documentations,
		Set<String> labels,
		Set<Task> tasks,
		Set<Milestone> milestones) {
	
	public LocalDate getDeadLine() {
		return this.startDate.plus(this.duration);
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
                .mapToLong(task -> task.duration().toDays())
                .sum();
    }

    public final long computeMaxCriticalPathValue() {
		return CriticalPathCalculator.compute(this);
    }
}
