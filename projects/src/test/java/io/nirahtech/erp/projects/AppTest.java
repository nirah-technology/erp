package io.nirahtech.erp.projects;


import java.time.Duration;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        
        Task task1 = TaskFactory.create("Found Empty Land")
                .duration(Duration.ofDays(5))
                .build();

        Task task2 = TaskFactory.create("Clear Land")
                .duration(Duration.ofDays(5*2))
                .parent(task1)
                .build();

        Task task3 = TaskFactory.create("Build Formation Tower")
                .duration(Duration.ofDays(5*4))
                .parent(task2)
                .build();

        Task task4 = TaskFactory.create("Buy weapons")
                .duration(Duration.ofDays(5*2))
                .parent(task3)
                .build();

        Task task5 = TaskFactory.create("Recruit Trainers")
                .duration(Duration.ofDays(5*4))
                .build();

        Task task6 = TaskFactory.create("Prepare training courses")
                .duration(Duration.ofDays(5*4*3))
                .build();

        Task task7 = TaskFactory.create("Recruit Students")
                .duration(Duration.ofDays(5*4))
                .build();

        Task task8 = TaskFactory.create("Train Students")
                .duration(Duration.ofDays(5*4*9))
                .requiredTasks(task4, task5, task6, task7)
                .build();

        Project clonesTrainingProject = new Project("Clones Traning", new HashSet<>(List.of(
            task1,
            task2,
            task3,
            task4,
            task5,
            task6,
            task7,
            task8
        )));
    }
}
