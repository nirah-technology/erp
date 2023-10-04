package io.nirahtech.erp.projects;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

public class MilestoneBuilder implements Builder<Milestone> {
    
		private UUID id = UUID.randomUUID();
		private String name = null;;
		private String description = null;
		private LocalDate startDate = null;
		private Duration duration = null;

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
        return new Milestone(new MilestoneIdentifier(this.id), this.name, this.description, this.startDate, this.duration);
    }

}
