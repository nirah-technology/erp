package io.nirahtech.erp.workingtime;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Cashable {

    BigDecimal computeGeneratedMoney();
    BigDecimal computeGeneratedMoney(LocalDate from, LocalDate to);
    BigDecimal computeGeneratedMoney(LocalDate from, LocalDate to, Project project);
    BigDecimal computeGeneratedMoney(Project project);
}
