import { useEffect, useState } from "react";
import "./Calendar.css";
import { Day } from "../../entity/Day";
import { Activity } from "../../entity/Activity";
import { CalendarViewType } from "../../common/CalendarViewType";
import { DayOfWeek, DayOfWeekParser } from "../../common/DayOfWeek";
import { Weak, WeakFactory } from "../../entity/Weak";
import { Link } from "react-router-dom";
import { ChronoField, LocalDateTime, Month } from "@nirahtech/datetime";

interface Properties {
    events: Array<Activity>;
    now: Day;
    minHour?: number,
    maxHour?: number,
    view?: CalendarViewType;

}
export function DailyCalendar({ now, events, minHour = 0, maxHour = 23 }: Properties) {
    const NOW: LocalDateTime = LocalDateTime.now();
    const TOTAL_ROWS: number = maxHour + 1;

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
                if (day.getDateTime().isEqual(activity.getStartingDate())) {
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
        let newWeakNumber: number = selectedWeakNumber - 1;
        setSelectedWeakNumber(newWeakNumber);
    }

    const changeToNextWeak = () => {
        let newWeakNumber: number = selectedWeakNumber + 1;
        setSelectedWeakNumber(newWeakNumber);
    }

    const displayWeak = () => {
        let days: Array<JSX.Element> = new Array();
        for (let hour = minHour; hour < TOTAL_ROWS - 0; hour++) {
            let hoursInDay: Array<JSX.Element> = new Array();
            for (let day = 0; day < 1 + 1; day++) {
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
                        {(dayOfTheWeak.getActivities().length > 0) ? (dayOfTheWeak.getActivities().length) : (null)}
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
                    <th className="center">
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
                    <th>
                        {DayOfWeek[DayOfWeekParser.parse(now.getDateTime().getDay()) - 1]}
                        <br />
                        ({now.getDateTime().getDay()} {Month.of((now.getDateTime().getMonth())).getName().substring(0, 3)} {now.getDateTime().getYear()})
                    </th>
                </tr>
            </thead>

            <tbody>
                {displayWeak()}
            </tbody>
        </table>
    );
}