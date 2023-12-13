import  TimeUnit  from './TimeUnit';
import Imputation from './Imputation';
import Project from './Project';

interface Imputable {
    impute(workingTime: Imputation): void;
    computeWorkedTimeUsingTimeUnit(timeUnit: TimeUnit): number;
    computeWorkedTimeFromDatesRangeUsingTimeUnit(from: Date | null, to: Date | null, timeUnit: TimeUnit): number;
    computeWorkedTimeOnProjectUsingTimeUnit(timeUnit: TimeUnit, project: Project | null): number;
    computeWorkedTimeOnProject(
        from: Date | null,
        to: Date | null,
        timeUnit: TimeUnit,
        project: Project | null
    ): number;
    listImputations(): ReadonlyArray<Imputation>;
}

export default Imputable