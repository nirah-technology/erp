package io.nirahtech.erp.projects.utils;

import java.util.Objects;
import java.util.OptionalLong;

import io.nirahtech.erp.projects.Project;
import io.nirahtech.erp.projects.Task;
import io.nirahtech.erp.projects.WorkPackage;

public final class CriticalPathCalculator {

    private CriticalPathCalculator() {
    }

    public static final long compute(final Project project) {
        final OptionalLong criticalPathInDays = project.getWorkPackages()
                .stream()
                .mapToLong(CriticalPathCalculator::compute)
                .max();
        return criticalPathInDays.orElse(0L);
    }

    public static final long compute(final WorkPackage workPackage) {
        final OptionalLong criticalPathInDays = workPackage.getTasks()
                .stream()
                .mapToLong(CriticalPathCalculator::compute)
                .max();
        return criticalPathInDays.orElse(0L);
    }

    public static final long compute(final Task task) {
        long days = 0;
        if (Objects.nonNull(task)) {
            if (Objects.isNull(task.getParent())) {
                days = task.getDuration().toDays();
            } else {
                days = task.getDuration().toDays() + CriticalPathCalculator.compute(task.getParent());
            }
        }
        return days;
    }
}
