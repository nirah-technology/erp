import { LocalDateTime, Period } from "@nirahtech/datetime";
import { Day } from "./Day";

export const TOTAL_DAYS_IN_WEAK: number = 7;


export class Weak {
    private days: Array<Day>;
    private weakNumber: number;
    constructor(weakNumber: number, days: Array<Day>) {
        this.weakNumber = weakNumber;
        this.days = days;
    }
    public getDays(): Array<Day> {
        return this.days;
    }
}

export class WeakFactory {

    
    public static create(weakNumber: number, year: number): Weak {
        let days: Array<Day> = new Array(TOTAL_DAYS_IN_WEAK);
        const MONDAY_VALUE: number = 2;
        let monday = LocalDateTime.of(year, 1, (MONDAY_VALUE + (weakNumber - 1) * TOTAL_DAYS_IN_WEAK));
        let dayOfTheWeak: LocalDateTime = monday;
        for (let index = 0; index < TOTAL_DAYS_IN_WEAK; index++) {
            days[index] = new Day(dayOfTheWeak);
            dayOfTheWeak = dayOfTheWeak.plusPeriod(Period.ofDays(1));
        }
        return new Weak(weakNumber, days);
    }
}