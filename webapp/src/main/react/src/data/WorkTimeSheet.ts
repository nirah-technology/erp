import Imputable from "./Imputable";
import Imputation from "./Imputation";
import Project from "./Project";
import TimeUnit from "./TimeUnit";

const TOTAL_MINUTES_IN_ONE_HOUR: number = 60;
const TOTAL_MINUTES_IN_ONE_DAY_OF_7_HOURS: number = TOTAL_MINUTES_IN_ONE_HOUR * 7;

class WorkTimeSheet implements Imputable {
    private readonly imputations: Set<Imputation> = new Set<Imputation>();

    constructor() {}

    public impute(workingTime: Imputation): void {
        this.imputations.add(workingTime);
    }

    public listImputations(): ReadonlyArray<Imputation> {
        return Array.from(this.imputations);
    }
    
    public computeWorkedTimeUsingTimeUnit(timeUnit: TimeUnit): number {
        return this.computeWorkedTimeFromDatesRangeUsingTimeUnit(null, null, timeUnit);
    }

    private computeWorkedTime(workedTimes: Imputation[] | null, timeUnit: TimeUnit): number {
        const workTimeInMinutes: number = workedTimes?.reduce((totalMinutes, workTime) => {
            switch (workTime.getTimeUnit()) {
                case TimeUnit.MINUTES:
                    return totalMinutes + workTime.getNumber();
                case TimeUnit.HOURS:
                    return totalMinutes + workTime.getNumber() * TOTAL_MINUTES_IN_ONE_HOUR;
                case TimeUnit.DAYS:
                    return totalMinutes + workTime.getNumber() * TOTAL_MINUTES_IN_ONE_DAY_OF_7_HOURS;
                default:
                    return totalMinutes;
            }
        }, 0) || 0;

        let workedTime: number = 0;
        switch (timeUnit) {
            case TimeUnit.MINUTES:
                workedTime = workTimeInMinutes;
                break;
            case TimeUnit.HOURS:
                workedTime = Math.floor(workTimeInMinutes / TOTAL_MINUTES_IN_ONE_HOUR);
                break;
            case TimeUnit.DAYS:
                workedTime = Math.floor(workTimeInMinutes / TOTAL_MINUTES_IN_ONE_DAY_OF_7_HOURS);
                break;
            default:
                break;
        }
        return workedTime;
    }

    public computeWorkedTimeFromDatesRangeUsingTimeUnit(from: Date | null, to: Date | null, timeUnit: TimeUnit): number {
        if (!from) {
            from = new Date();
            from.setFullYear(from.getFullYear() - 5);
        }
        if (!to) {
            to = new Date();
        }
        if (from.getTime() > to.getTime()) {
            const temp: Date | null = to;
            to = from;
            from = temp;
        }

        const realFrom: Date = from as Date;
        const realTo: Date = to as Date;

        const filteredImputations: Imputation[] = Array.from(this.imputations).filter((workTime) =>
            workTime.getDate() > realFrom && workTime.getDate() < realTo
        );
        return this.computeWorkedTime(filteredImputations, timeUnit);
    
    }

    public computeWorkedTimeOnProjectUsingTimeUnit(timeUnit: TimeUnit, project: Project | null): number {
        return this.computeWorkedTimeOnProject(null, null, timeUnit, project);
    }

    public computeWorkedTimeOnProject(from: Date | null, to: Date | null, timeUnit: TimeUnit, project: Project | null): number {
        if (!project) {
            return 0;
        }
    
        if (!from) {
            from = new Date();
            from.setFullYear(from.getFullYear() - 5);
        }
    
        if (!to) {
            to = new Date();
        }
    
        if (from.getTime() > to.getTime()) {
            const temp: Date | null = to;
            to = from;
            from = temp;
        }
    
        const realFrom: Date = from as Date;
        const realTo: Date = to as Date;
    
        const filteredImputations: Imputation[] = Array.from(this.imputations).filter((workTime) =>
            workTime.getDate() > realFrom && workTime.getDate() < realTo && workTime.getProject() === project
        );
        return this.computeWorkedTime(filteredImputations, timeUnit);    
        
    }
}
export default WorkTimeSheet;