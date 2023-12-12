package io.nirahtech.erp.workingtime;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class WorkTimesheet implements Imputable {
    private static final int TOTAL_MINUTES_IN_ONE_HOUR = 60;
    private static final int TOTAL_MINUTES_IN_ONE_DAY_OF_7_HOURS = TOTAL_MINUTES_IN_ONE_HOUR * 7;

    private final Set<Imputation> imputations = new HashSet<>();

    WorkTimesheet() {

    }

    @Override
    public final void impute(final Imputation workingTime) {
        this.imputations.add(workingTime);
    }

    @Override
    public final Collection<Imputation> listImputations() {
        return Collections.unmodifiableCollection(this.imputations);
    }

    @Override
    public final int computeWorkedTime(final TimeUnit timeUnit) {
        return this.computeWorkedTime(null, null, timeUnit);
    }

    private final int computeWorkedTime(final Collection<Imputation> workedTimes, final TimeUnit timeUnit) {
        final AtomicInteger workTimeInMinutes = new AtomicInteger(0);

        workedTimes.forEach(workTime -> {
            switch (workTime.getTimeUnit()) {
                case MINUTES:
                    workTimeInMinutes.getAndAdd(workTime.getNumber());
                    break;
                case HOURS:
                    workTimeInMinutes.getAndAdd(workTime.getNumber() * TOTAL_MINUTES_IN_ONE_HOUR);
                    break;
                case DAYS:
                    workTimeInMinutes.getAndAdd(workTime.getNumber() * TOTAL_MINUTES_IN_ONE_DAY_OF_7_HOURS);
                    break;
                default:
                    break;
            }
        });
        int workedTime = 0;
        switch (timeUnit) {
            case MINUTES:
                workedTime = workTimeInMinutes.get();
                break;
            case HOURS:
                workedTime = workTimeInMinutes.get() / TOTAL_MINUTES_IN_ONE_HOUR;
                break;
            case DAYS:
                workedTime = workTimeInMinutes.get() / TOTAL_MINUTES_IN_ONE_DAY_OF_7_HOURS;
            default:
                break;
        }
        return workedTime;
    }

    @Override
    public final int computeWorkedTime(LocalDate from, LocalDate to, final TimeUnit timeUnit) {
        if (Objects.isNull(from) && Objects.isNull(to)) {
            from = LocalDate.now().minusYears(5);
            to = LocalDate.now();
        } else if (Objects.isNull(from)) {
            from = LocalDate.now().minusYears(5);
        } else if (Objects.isNull(to)) {
            to = LocalDate.now();
        }
        if (from.isAfter(to)) {
            LocalDate temp = to;
            from = to;
            to = temp;
        }
        
        final LocalDate realFrom = from;
        final LocalDate realTo = to;

        final List<Imputation> filteredImputations = this.imputations.stream()
                .filter(workTime -> workTime.getDate().isAfter(realFrom))
                .filter(workTime -> workTime.getDate().isBefore(realTo))
                .collect(Collectors.toList());
        return this.computeWorkedTime(filteredImputations, timeUnit);
        
    }

    @Override
    public final int computeWorkedTimeOnProject(LocalDate from, LocalDate to, TimeUnit timeUnit, Project project) {
        if (Objects.isNull(project)) {
            return 0;
        }
        if (Objects.isNull(from) && Objects.isNull(to)) {
            from = LocalDate.now().minusYears(5);
            to = LocalDate.now();
        } else if (Objects.isNull(from)) {
            from = LocalDate.now().minusYears(5);
        } else if (Objects.isNull(to)) {
            to = LocalDate.now();
        }
        if (from.isAfter(to)) {
            LocalDate temp = to;
            from = to;
            to = temp;
        }
        
        final LocalDate realFrom = from;
        final LocalDate realTo = to;

        final List<Imputation> filteredImputations = this.imputations.stream()
                .filter(workTime -> workTime.getDate().isAfter(realFrom))
                .filter(workTime -> workTime.getDate().isBefore(realTo))
                .filter(workTime -> workTime.getProject().equals(project))
                .collect(Collectors.toList());
        return this.computeWorkedTime(filteredImputations, timeUnit);
    }

    @Override
    public int computeWorkedTimeOnProject(TimeUnit timeUnit, Project project) {
        return this.computeWorkedTimeOnProject(null, null, timeUnit, project);
    }
}
