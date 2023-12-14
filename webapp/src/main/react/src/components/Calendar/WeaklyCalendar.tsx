import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "./Calendar.css";
import { Activity } from "../../entity/Activity";
import { Day } from "../../entity/Day";
import { CalendarViewType } from "../../common/CalendarViewType";
import { Weak, WeakFactory } from "../../entity/Weak";
import { ChronoField, LocalDateTime, Period } from "@nirahtech/datetime";

interface Properties {
    events: Array<Activity>;
    now: Day;
    minHour?: number,
    maxHour?:number,
    view?: CalendarViewType;

}

export function WeaklyCalendar({ now, events, minHour=0, maxHour=23 }: Properties) {
    const NOW: LocalDateTime = LocalDateTime.now();
    const TOTAL_ROWS: number = maxHour+1;
    const TOTAL_COLUMNS: number = 7;


    // const getNumberOfTheWeak = (date: Date) => {
    //     let now: Date = date;
    //     let start: Date = new Date(now.getFullYear(), 0, 1);
    //     let days = Math.floor((now.getTime() - start.getTime()) /
    //         (24 * 60 * 60 * 1000));
    //     return Math.ceil(days / 7);
    // }

    const [date, setDate] = useState(NOW);
    const [workingDate, setWorkingDate] = useState(NOW);
    const [selectedWeakNumber, setSelectedWeakNumber] = useState(NOW.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
    const [weakNumber, setWeakNumber] = useState(selectedWeakNumber);
    const [weak, setWeak] = useState(WeakFactory.create(weakNumber, workingDate.getYear()));



    const synchronizeActivitiesForCurrentWeak = () => {
        let newWeak: Weak = WeakFactory.create(weakNumber, date.getYear());
        let eventsOfTheWeak: Array<Activity> = events.filter(
            (event) => (newWeak.getDays()[0].getDateTime() <= event.getStartingDate()) && (event.getStartingDate() <= newWeak.getDays()[newWeak.getDays().length - 1].getDateTime()));
        for (const day of newWeak.getDays()) {
            let activitiesOfTheDay: Array<Activity> = new Array();
            eventsOfTheWeak.forEach((activity) => {
                if (day.getDateTime().toLocalDate().isEqual(activity.getStartingDate().toLocalDate())) {
                    activitiesOfTheDay.push(activity);
                }
            })
            day.setActivities(activitiesOfTheDay);
        }
        setWeak(newWeak);
    }

    useEffect(() => {
        setWorkingDate(date);
        setWeak(WeakFactory.create(weakNumber, date.getYear()));
        synchronizeActivitiesForCurrentWeak();
    }, [date]);

    useEffect(() => {
        synchronizeActivitiesForCurrentWeak();
    }, [events]);

    useEffect(() => {
        setWeakNumber(selectedWeakNumber);
        setWeak(WeakFactory.create(selectedWeakNumber, date.getYear()));
        synchronizeActivitiesForCurrentWeak();
    }, [selectedWeakNumber]);


    const changeToPreviousWeak = () => {
        let previousWeek: LocalDateTime = date.minusPeriod(Period.ofWeeks(1));
        setSelectedWeakNumber(previousWeek.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        setDate(previousWeek);
    }

    const changeToNextWeak = () => {
        let previousWeek: LocalDateTime = date.plusPeriod(Period.ofWeeks(1));
        setSelectedWeakNumber(previousWeek.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        setDate(previousWeek);
    }

    const displayWeak = () => {
        let days: Array<JSX.Element> = new Array();
        for (let hour = minHour; hour < TOTAL_ROWS - 0; hour++) {
            let hoursInDay: Array<JSX.Element> = new Array();
            for (let day = 0; day < TOTAL_COLUMNS + 1; day++) {
                if (day === 0) {
                    hoursInDay.push((<td className="hours">{hour}:00</td>));
                } else {
                    let dayOfTheWeak: Day = weak.getDays()[day - 1];
                    let dateTime: LocalDateTime = dayOfTheWeak.getDateTime().withHours(hour);
                    let cellContent = (<Link
                        to={String("/planning/")
                            .concat(String(dateTime.getYear()))
                            .concat("/")
                            .concat(String(dateTime.getMonth()))
                            .concat("/")
                            .concat(String(dateTime.getDay()))
                        }
                    >
                        {(dayOfTheWeak.getActivities().length > 0) ? (dayOfTheWeak.getActivities().length) : (null) } 
                    </Link>);
                    if (day === NOW.getDay()) {
                        if (hour === NOW.getHour()) {
                            hoursInDay.push((<td className="lime accent-4">
                                {cellContent}
                            </td>));
                        } else {
                            hoursInDay.push((<td className="lime lighten-4">{cellContent}</td>));
                        }
                    } else {
                        hoursInDay.push((<td>{cellContent}</td>));
                    }
                }
            }
            days.push((<tr>
                {hoursInDay.map((hour) => hour)}
            </tr>));

        }
        return days.map((day) => day);
    }

    return (
        <table>
            <thead>
                <tr>
                    <th colSpan={TOTAL_COLUMNS} className="center">
                        <button
                            type="button"
                            onClick={changeToPreviousWeak}
                        >
                            <span className="material-symbols-outlined">chevron_left</span>
                        </button>
                        {`Semaine ${selectedWeakNumber}`}

                        <button
                            type="button"
                            onClick={changeToNextWeak}
                        >
                            <span className="material-symbols-outlined">chevron_right</span>
                        </button>
                    </th>
                </tr>
                <tr>
                    <th></th>
                    {weak.getDays().map((day) => (<th>
                        {day.getDateTime().toLocalDate().getDayOfWeek().getName()}
                        <br />
                        ({day.getDateTime().getDay()} {day.getDateTime().toLocalDate().getMonth().getName().substring(0, 3)} {day.getDateTime().getYear()})
                    </th>))}
                </tr>
            </thead>

            <tbody>
                {displayWeak()}
            </tbody>
        </table>
    );
}