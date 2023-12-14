export enum CalendarViewType {
    MONTH,
    WEAK,
    DAY
}

export class CalendarViewTypeParser {
    private constructor() { }
    static parse(month: number): CalendarViewType {
        let result: CalendarViewType;
        switch (month) {
            case 0:
                result = CalendarViewType.MONTH
                break;
            case 1:
                result = CalendarViewType.WEAK
                break;
            case 2:
                result = CalendarViewType.DAY
                break;
            default:
                throw new Error("Invalid");
        }
        return result;
    }
}