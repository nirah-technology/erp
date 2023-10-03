package io.nirahtech.erp.projects;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public record Project(String name, Set<Task> tasks) {
    public int getWorkingDays() {
        final AtomicLong counter = new AtomicLong(0);
        tasks.forEach(task -> {
            long days = task.duration().getSeconds() % (60*60);
            counter.addAndGet(days);
        });
        return counter.intValue();
    }
}