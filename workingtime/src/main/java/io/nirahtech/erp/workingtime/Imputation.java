package io.nirahtech.erp.workingtime;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public record Imputation(LocalDate date, int number, TimeUnit timeUnit, Project project, String details) {
    
    
}
