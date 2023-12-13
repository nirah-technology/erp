import Imputation from './Imputation';
import LocalDateTime from './LocalDateTime';
import Project from './Project';
import TimeUnit from './TimeUnit';

interface Imputable {
    impute(workingTime: Imputation): void;
    computeWorkedTimeUsingTimeUnit(timeUnit: TimeUnit): number;
    computeWorkedTimeFromDatesRangeUsingTimeUnit(from: LocalDateTime | null, to: LocalDateTime | null, timeUnit: TimeUnit): number;
    computeWorkedTimeOnProjectUsingTimeUnit(timeUnit: TimeUnit, project: Project | null): number;
    computeWorkedTimeOnProject(
        from: LocalDateTime | null,
        to: LocalDateTime | null,
        timeUnit: TimeUnit,
        project: Project | null
    ): number;
    listImputations(): ReadonlyArray<Imputation>;
}

export default Imputable