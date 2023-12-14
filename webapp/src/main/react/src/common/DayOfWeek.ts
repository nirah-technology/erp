export enum DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

export class DayOfWeekParser {
    private constructor() { }
    static parse(day: number): DayOfWeek {
        let result: DayOfWeek;
        switch (day) {
            case 0:
                result = DayOfWeek.MONDAY
                break;
            case 1:
                result = DayOfWeek.TUESDAY
                break;
            case 2:
                result = DayOfWeek.WEDNESDAY
                break;
            case 3:
                result = DayOfWeek.THURSDAY
                break;
            case 4:
                result = DayOfWeek.FRIDAY
                break;
            case 5:
                result = DayOfWeek.SATURDAY
                break;
            case 6:
                result = DayOfWeek.SUNDAY
                break;
            default:
                throw new Error("Invalid");
        }
        return result;
    }
}