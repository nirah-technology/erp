import { useEffect, useState } from "react";
import "./Calendar.css";
import { Day } from "../../entity/Day";
import { Activity } from "../../entity/Activity";
import { CalendarViewType } from "../../common/CalendarViewType";
import { DayOfWeek } from "../../common/DayOfWeek";
import { LocalDateTime, Month, Period } from "@nirahtech/datetime";

interface Properties {
    events: Array<Activity>;
    now: Day;
    minHour?: number,
    maxHour?:number,
    view?: CalendarViewType;

}
export function MonthlyCalendar({ now, events }: Properties) {

    const TOTAL_MONTHS: number = 12;
    const NOW: LocalDateTime = LocalDateTime.now();
    const TOTAL_ROWS: number = 6;
    const TOTAL_COLUMNS: number = 7;

    const [date, setDate] = useState(NOW);
    const [workingDate, setWorkingDate] = useState(NOW);
    const [activities, setActivities] = useState(new Array<Activity>());

    useEffect(() => {
        setWorkingDate(date);
    }, [date])


    const changeToPreviousMonth = () => {
        setDate(workingDate.minusPeriod(Period.ofMonths(1)));
    }

    const changeToNextMonth = () => {
        setDate(workingDate.plusPeriod(Period.ofMonths(1)));
    }


    const getFirstDayOfWeakOfMonth = (year: number, month: number) => {
        return new Date(year, month, 1).getDay() - 1;
    }

    const getTotalDaysInMonth = (year: number, month: number) => {
        return new Date(year, month, 0).getDate();
    }

    const displayDaysNameHeader = () => {
        const daysNames: Array<JSX.Element> = new Array();
        for (let index = 0; index < TOTAL_COLUMNS; index++) {
            daysNames.push(<th>{String(DayOfWeek[index])}</th>)
        }
        return daysNames.map((dayName) => dayName)
    }

    const displayMonth = (month: number) => {
        const totalDaysInMonth: number = getTotalDaysInMonth(workingDate.getYear(), month);
        const firstDayOfWeakOfMonth: number = getFirstDayOfWeakOfMonth(workingDate.getYear(), month);

        let weaks: Array<JSX.Element> = new Array();
        let workingDay: number = 0 - firstDayOfWeakOfMonth;
        for (let index = 0; index < TOTAL_ROWS; index++) {
            let daysOfWeak: Array<JSX.Element> = new Array();
            for (let index = 0; index < TOTAL_COLUMNS; index++) {
                if ((workingDay >= 0) && (workingDay < totalDaysInMonth)) {
                    if (workingDate.getDay() == workingDay + 1) {
                        daysOfWeak.push((<td className="lime accent-4">{workingDay + 1}</td>));
                    } else {
                        daysOfWeak.push((<td>{workingDay + 1}</td>));
                    }
                } else {
                    daysOfWeak.push((<td className="grey darken-1"></td>));
                }
                workingDay++;
            }
            weaks.push((<tr>
                {daysOfWeak.map((day) => day)}
            </tr>));
        }
        return weaks.map((weak) => weak);
    }

    return (
        <table>
            <thead>
                <tr>
                    <th colSpan={TOTAL_COLUMNS} className="center">
                        <button
                            type="button"
                            className="waves-effect waves-light btn black"
                            onClick={changeToPreviousMonth}>
                            {Month.of(workingDate.getMonth() - 1).getName()}
                            <i className="material-icons right">chevron_left</i>
                        </button>
                        {Month.of(workingDate.getMonth()).getName() + " " + workingDate.getYear()}
                        <button
                            type="button"
                            className="waves-effect waves-light btn black"
                            onClick={changeToNextMonth}>
                            <i className="material-icons left">chevron_right</i>
                            {Month.of(workingDate.getMonth() + 1).getName()}
                        </button>
                    </th>
                </tr>
                <tr>
                    {displayDaysNameHeader()}
                </tr>
            </thead>
            <tbody>
                {displayMonth(workingDate.getMonth())}
            </tbody>
        </table>
    );
}