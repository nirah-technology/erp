import { useEffect, useState } from "react";
import "./Calendar.css";
import { DailyCalendar } from "./DailyCalendar";
import { MonthlyCalendar } from "./MonthlyCalendar";
import { WeaklyCalendar } from "./WeaklyCalendar";
import { Activity } from "../../entity/Activity";
import { Day } from "../../entity/Day";
import { CalendarViewType } from "../../common/CalendarViewType";

interface Properties {
    events: Array<Activity>;
    now: Day;
    minHour?: number,
    maxHour?:number,
    view?: CalendarViewType;

}

export function Calendar({ now, events, view }: Properties) {

    const [activities, setActivities] = useState(new Array<Activity>());
    const [activeView, setActiveView] = useState(view);
    const [currentDay, setCurretnDay] = useState(now);
    const [minHour, setMinHour] = useState(0);
    const [maxHour, setMaxHour] = useState(23);

    useEffect(() => {
        setActiveView(view);
    }, [view])


    useEffect(() => {
        setActivities(events);
    }, [events])

    let calendar;

    switch (activeView) {
        case CalendarViewType.MONTH:
            calendar = (<MonthlyCalendar now={currentDay} events={activities} minHour={minHour} maxHour={maxHour} />)
            break;
        case CalendarViewType.WEAK:
            calendar = (<WeaklyCalendar now={currentDay} events={activities} minHour={minHour} maxHour={maxHour} />)
            break;
        case CalendarViewType.DAY:
            calendar = (<DailyCalendar now={currentDay} events={activities} minHour={minHour} maxHour={maxHour} />)
            break;
        default:
            calendar = (<></>)
            break;
    }
    return (<div className="Calendar-Component">{calendar}</div>);
}