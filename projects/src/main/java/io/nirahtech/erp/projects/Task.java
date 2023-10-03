package io.nirahtech.erp.projects;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record Task(
    UUID id,
    String name,
    String details,
    Task parent,
    Set<Task> subTasks,
    Set<Task> requiredTasks,
    LocalDate startDate,
    Duration duration,
    int workers
) {
    
}
