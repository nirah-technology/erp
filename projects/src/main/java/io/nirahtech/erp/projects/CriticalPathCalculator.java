package io.nirahtech.erp.projects;

import java.util.Objects;
import java.util.OptionalLong;

public final class CriticalPathCalculator {

    private CriticalPathCalculator() {
    }

    public static final long compute(Project project) {
        final OptionalLong criticalPathInDays = project.packages()
                .stream()
                .mapToLong(CriticalPathCalculator::compute)
                .max();
        return criticalPathInDays.orElse(0L);
    }

    public static final long compute(WorkPackage workPackage) {
        final OptionalLong criticalPathInDays = workPackage.tasks()
                .stream()
                .mapToLong(CriticalPathCalculator::compute)
                .max();
        return criticalPathInDays.orElse(0L);
    }

    public static final long compute(Task task) {
        long days = 0;
        if (Objects.nonNull(task)) {
            if (Objects.isNull(task.parent())) {
                days = task.duration().toDays();
            } else {
                days = task.duration().toDays() + CriticalPathCalculator.compute(task.parent());
            }
        }
        return days;
    }
}
