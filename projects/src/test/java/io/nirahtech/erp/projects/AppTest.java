package io.nirahtech.erp.projects;

import java.time.Duration;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {

		Task task1 = TaskFactory.create().name("Found Empty Land")
				.duration(Duration.ofDays(5))
				.build();

		Task task2 = TaskFactory.create().name("Clear Land")
				.duration(Duration.ofDays(5 * 2))
				.parent(task1)
				.build();

		Task task3 = TaskFactory.create().name("Build Formation Tower")
				.duration(Duration.ofDays(5 * 4))
				.parent(task2)
				.build();

		Task task4 = TaskFactory.create().name("Buy weapons")
				.duration(Duration.ofDays(5 * 2))
				.parent(task3)
				.build();

		Task task5 = TaskFactory.create().name("Recruit Trainers")
				.duration(Duration.ofDays(5 * 4))
				.build();

		Task task6 = TaskFactory.create().name("Prepare training courses")
				.duration(Duration.ofDays(5 * 4 * 3))
				.build();

		Task task7 = TaskFactory.create().name("Recruit Students")
				.duration(Duration.ofDays(5 * 4))
				.build();

		Task task8 = TaskFactory.create().name("Train Students")
				.duration(Duration.ofDays(5 * 4 * 9))
				.requiredTasks(task4, task5, task6, task7)
				.build();

		// WorkPackage package1 = new WorkPackage(
		// 	new WorkPackageIdentifier(UUID.randomUUID()), 
		// 	"Lot 1", 
		// 	null, 
		// 	null, 
		// 	LocalDate.now(), 
		// 	Duration.ofDays(1), 
		// 	Status.IDLE, 
		// 	Arrays.asList(), 
		// 	Arrays.asList(), 
		// 	Arrays.asList(
		// 		task1,
		// 		task2,
		// 		task3,
		// 		task4,
		// 		task5,
		// 		task6,
		// 		task7,
		// 		task8), 
		// 	Arrays.asList());
		Project clonesTrainingProject = new ProjectBuilder()
				.name("Clones Traning")
				// .workPackages(package1)
				.build();


		System.out.println(clonesTrainingProject.computeMaxCriticalPathValue());
	}
}
