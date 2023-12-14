import { LocalDateTime } from "@nirahtech/datetime";
import { Activity } from "./Activity";

export class Day {
    private dateTime: LocalDateTime;
    private activities: Array<Activity>;

    constructor(dateTime: LocalDateTime) {
        this.dateTime = dateTime;
        this.activities = new Array();
    }

    public getDateTime(): LocalDateTime {
        return this.dateTime;
    }
    public getActivitiesBefore(dateTime: LocalDateTime): Array<Activity> {
        return this.activities.filter((activity) => activity.getStartingDate() <= dateTime);
    }
    
    public getActivitiesAfter(dateTime: LocalDateTime): Array<Activity> {
        return this.activities.filter((activity) => activity.getStartingDate() >= dateTime);
    }

    public getActivities(from: LocalDateTime|null=null, to:LocalDateTime|null=null): Array<Activity> {
        let result: Array<Activity> = new Array();
        let isProcessed: boolean = false;

        if (from != null && to != null) {
            let start: LocalDateTime = from;
            let end: LocalDateTime = to;
            result = this.activities.filter((activity) => (activity.getStartingDate().isAfter(start)) && activity.getStartingDate().isBefore(end));
            isProcessed = true;
        } else if (from != null) {
            result = this.getActivitiesAfter(from);
            isProcessed = true;
        } else if (to != null) {
            result = this.getActivitiesBefore(to);
            isProcessed = true;
        }

        if (!isProcessed) {
            result = this.activities;
        }
        return result;
    }

    public setActivities(activities: Array<Activity>) {
        this.activities = activities;
    }
}