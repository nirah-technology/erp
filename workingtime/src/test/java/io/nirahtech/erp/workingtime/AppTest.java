package io.nirahtech.erp.workingtime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

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
        final Company empire = new Company("Empire");

        final Project deathStarProject = empire.launchProject("Death Star");

        final Employee john = new Employee("John", "DOE", BigDecimal.valueOf(24.72F));
        final Employee jane = new Employee("Jane", "DOE", BigDecimal.valueOf(23.08F));

        empire.recruit(john);
        empire.recruit(jane);

        john.impute(new Imputation(LocalDate.now().minusDays(60), 7, TimeUnit.DAYS, deathStarProject, null));
        john.impute(new Imputation(LocalDate.now().minusDays(30), 5, TimeUnit.HOURS, deathStarProject, null));
        john.impute(new Imputation(LocalDate.now().minusDays(29), 6, TimeUnit.HOURS, deathStarProject, null));
        john.impute(new Imputation(LocalDate.now().minusDays(28), 6, TimeUnit.HOURS, deathStarProject, null));
        john.impute(new Imputation(LocalDate.now().minusDays(27), 7, TimeUnit.HOURS, deathStarProject, null));
        john.impute(new Imputation(LocalDate.now().minusDays(28), 45, TimeUnit.MINUTES, deathStarProject, null));
        john.impute(new Imputation(LocalDate.now().minusDays(2), 3, TimeUnit.HOURS, deathStarProject, null));
        john.impute(new Imputation(LocalDate.now().minusDays(1), 7, TimeUnit.HOURS, deathStarProject, null));

        jane.impute(new Imputation(LocalDate.now().minusDays(1), 5, TimeUnit.HOURS, deathStarProject, null));

        BigDecimal generatedMoneyByJohn = john.computeGeneratedMoney(deathStarProject);
        BigDecimal generatedMoneyByJane = jane.computeGeneratedMoney(deathStarProject);
        BigDecimal generatedMoneyByEmpire = empire.computeGeneratedMoney(deathStarProject);

        System.out.println(generatedMoneyByJohn);
        System.out.println(generatedMoneyByJane);
        System.out.println(generatedMoneyByEmpire);
        
        empire.fire(john);
        empire.fire(jane);
    }
}
