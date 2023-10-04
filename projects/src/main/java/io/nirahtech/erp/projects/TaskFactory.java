package io.nirahtech.erp.projects;

public final class TaskFactory {
    private TaskFactory() { }

    public static final TaskBuilder create() {
        return new TaskBuilder();
    }
}
