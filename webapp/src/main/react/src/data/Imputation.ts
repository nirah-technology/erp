import Project from "./Project";
import TimeUnit from "./TimeUnit";

class Imputation {
    private readonly date: Date;
    private readonly number: number;
    private readonly timeUnit: TimeUnit;
    private readonly project: Project;
    private readonly details: string;

    constructor(date: Date, number: number, timeUnit: TimeUnit, project: Project, details: string) {
        this.date = date;
        this.number = number;
        this.timeUnit = timeUnit;
        this.project = project;
        this.details = details;
    }

    public getDate(): Date {
        return this.date;
    }

    public getNumber(): number {
        return this.number;
    }

    public getTimeUnit(): TimeUnit {
        return this.timeUnit;
    }

    public getProject(): Project {
        return this.project;
    }

    public getDetails(): string {
        return this.details;
    }
}

export default Imputation;