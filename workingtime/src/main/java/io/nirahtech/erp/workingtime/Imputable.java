package io.nirahtech.erp.workingtime;

import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public interface Imputable {
    void impute(final Imputation workingTime);
    int computeWorkedTime(TimeUnit timeUnit);
    int computeWorkedTime(LocalDate from, LocalDate to, TimeUnit timeUnit);
    
    int computeWorkedTimeOnProject(TimeUnit timeUnit, Project project);
    int computeWorkedTimeOnProject(LocalDate from, LocalDate to, TimeUnit timeUnit, Project project);

    Collection<Imputation> listImputations();
}
