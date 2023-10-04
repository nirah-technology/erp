package io.nirahtech.erp.projects;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record Task(
    UUID id,
    String name,
    String details,
    Status status,
    Task parent,
    List<Task> subTasks,
    Set<Task> requiredTasks,
    LocalDate startDate,
    Duration duration,
    Priority prority,
    Set<String> labels,
    Set<File> documentations,
    ProjectMember referent,
    Set<ProjectMember> workers,
    int maxWorkers
) {

    public final LocalDate getDeadLine() {
        return this.startDate.plusDays(this.duration.toDays());
    }
    
}
