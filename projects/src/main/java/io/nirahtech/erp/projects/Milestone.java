package io.nirahtech.erp.projects;

import java.time.Duration;
import java.time.LocalDate;

public final record Milestone(
		MilestoneIdentifier id,
		String name,
		String description,
		LocalDate startDate,
		Duration duration) {
}
