package io.nirahtech.erp.projects;

public final class TaskFactory {
    private TaskFactory() { }

    public static final TaskBuilder create(final String name) {
        return new TaskBuilder(name);
    }
}
