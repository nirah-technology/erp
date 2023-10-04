package io.nirahtech.erp.projects;

import java.time.Duration;
import java.time.LocalDate;

public record Milestone(
		String name,
		String description,
		LocalDate startDate,
		Duration duration) {
}
