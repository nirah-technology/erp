import { LocalDateTime } from "@nirahtech/datetime";
import { DayOfWeek } from "../common/DayOfWeek";
import { PeriodicityType } from "../common/PeriodicityType";

export abstract class Periodicity {
    protected startingDate: LocalDateTime;
    protected terminateDate: LocalDateTime;
    protected noEnd: boolean;
    protected periodicityType: PeriodicityType;

    protected constructor(startingDate: LocalDateTime, terminateDate: LocalDateTime, noEnd: boolean, periodicityType: PeriodicityType) {
        this.startingDate = startingDate;
        this.terminateDate = terminateDate;
        this.periodicityType = periodicityType;
        this.noEnd = noEnd;
    }

}

export class DailyPeriodicity extends Periodicity {
    private eachDayOccurence: number; // All 1 days, All 2 days
    // OR
    private isAllDaysOfWeak: boolean; // All days

    public constructor(startingDate: LocalDateTime, terminateDate: LocalDateTime, noEnd: boolean, eachDayOccurence: number, isAllDaysOfWeak: boolean) {
        super(startingDate, terminateDate, noEnd, PeriodicityType.DAILY);
        this.eachDayOccurence = eachDayOccurence;
        this.isAllDaysOfWeak = isAllDaysOfWeak;
    }
}

export class WeaklyPeriodicity extends Periodicity {
    private eachWeakOccurence: number; // All 1 weak, all 2 weaks, etc
    private daysOfTheWeak: Array<DayOfWeek>; // Mon, THu, Wed, etc

    public constructor(startingDate: LocalDateTime, terminateDate: LocalDateTime, noEnd: boolean, eachWeakOccurence: number, daysOfTheWeak: Array<DayOfWeek>) {
        super(startingDate, terminateDate, noEnd, PeriodicityType.WEAKLY);
        this.eachWeakOccurence = eachWeakOccurence;
        this.daysOfTheWeak = daysOfTheWeak;
    }

}

export class MonthlyPeriodicity extends Periodicity {
    private eachDayOfTheMonth: number; // Le 29 ...
    private eachMonthInterval: number; // ... tous les 2 mois
    // OR
    private dayOfWeakOnMonth: number; //Le DEUXIEME...
    private dayOfWeak: DayOfWeek; // ... MARDI
    // Tout les  X mois -> eachMonthInterval

    public constructor(startingDate: LocalDateTime, terminateDate: LocalDateTime, noEnd: boolean, eachDayOfTheMonth: number, eachMonthInterval: number, dayOfWeakOnMonth: number, dayOfWeak: DayOfWeek) {
        super(startingDate, terminateDate, noEnd, PeriodicityType.MONTHLY);
        this.eachDayOfTheMonth = eachDayOfTheMonth;
        this.eachMonthInterval = eachMonthInterval;
        this.dayOfWeakOnMonth = dayOfWeakOnMonth;
        this.dayOfWeak = dayOfWeak;
    }

}

export class YearlyPeriodicity extends Periodicity {
    public constructor(startingDate: LocalDateTime, terminateDate: LocalDateTime, noEnd: boolean) {
        super(startingDate, terminateDate, noEnd, PeriodicityType.YEARLY);
    }

}