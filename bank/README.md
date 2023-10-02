```java

package io.nirahtech.erp.workingtime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class Program {
    public static void main(String[] args) {
        final Company adelya = new Company("Adelya");

        final Project frameworkProject = adelya.launchProject("Framework");

        final Employee nicolas = new Employee("Nicolas", "METIVIER", BigDecimal.valueOf(24.72F));
        final Employee vincent = new Employee("Vicent", "RETORE", BigDecimal.valueOf(23.08F));

        adelya.recruit(nicolas);
        adelya.recruit(vincent);

        nicolas.impute(new Imputation(LocalDate.now().minusDays(60), 7, TimeUnit.DAYS, frameworkProject));
        nicolas.impute(new Imputation(LocalDate.now().minusDays(30), 5, TimeUnit.HOURS, frameworkProject));
        nicolas.impute(new Imputation(LocalDate.now().minusDays(29), 6, TimeUnit.HOURS, frameworkProject));
        nicolas.impute(new Imputation(LocalDate.now().minusDays(28), 6, TimeUnit.HOURS, frameworkProject));
        nicolas.impute(new Imputation(LocalDate.now().minusDays(27), 7, TimeUnit.HOURS, frameworkProject));
        nicolas.impute(new Imputation(LocalDate.now().minusDays(28), 45, TimeUnit.MINUTES, frameworkProject));
        nicolas.impute(new Imputation(LocalDate.now().minusDays(2), 3, TimeUnit.HOURS, frameworkProject));
        nicolas.impute(new Imputation(LocalDate.now().minusDays(1), 7, TimeUnit.HOURS, frameworkProject));

        vincent.impute(new Imputation(LocalDate.now().minusDays(1), 5, TimeUnit.HOURS, frameworkProject));

        BigDecimal generatedMoneyByNicolas = nicolas.computeGeneratedMoney(frameworkProject);
        BigDecimal generatedMoneyByVincent = vincent.computeGeneratedMoney(frameworkProject);
        BigDecimal generatedMoneyByAdelya = adelya.computeGeneratedMoney(frameworkProject);

        System.out.println(generatedMoneyByNicolas);
        System.out.println(generatedMoneyByVincent);
        System.out.println(generatedMoneyByAdelya);
        adelya.fire(nicolas);
        adelya.fire(vincent);
    }
}

```