package io.nirahtech.erp.projects;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MilestoneBuilder implements Builder<Milestone> {

    private UUID id = UUID.randomUUID();
    private String name;
    private Duration duration;
    private String description = null;
    private ProjectMember referent = null;
    private LocalDate startDate = LocalDate.now();
    private Status status = Status.IDLE;
    private Set<File> documentations = new HashSet<>();
    private Set<String> labels = new HashSet<>();
    private Map<Status, Runnable> eventListeners = new HashMap<>();

    public MilestoneBuilder() {

    }

    public final MilestoneBuilder id(final UUID id) {
        this.id = id;
        return this;
    }

    public final MilestoneBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public final MilestoneBuilder description(final String description) {
        this.description = description;
        return this;
    }

    public final MilestoneBuilder startDate(final LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public final MilestoneBuilder duration(final Duration duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public final Milestone build() {
        return new Milestone(new MilestoneIdentifier(id), name, description, referent, startDate, duration, status, documentations, labels);
    }

}
