class LocalTime {
    private hour: number;
    private minute: number;
    private second: number;
    private milliseconds: number;

    constructor(hour: number, minute: number, second: number, milliseconds: number) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.milliseconds = milliseconds;
    }

    getHour(): number {
        return this.hour;
    }

    getMinute(): number {
        return this.minute;
    }

    getSecond(): number {
        return this.second;
    }

    getMilliseconds(): number {
        return this.milliseconds;
    }

    toString(): string {
        return `${this.hour}:${this.minute}:${this.second}`;
    }

    isEqual(otherTime: LocalTime): boolean {
        return this.toString() === otherTime.toString();
    }

    isBefore(otherTime: LocalTime): boolean {
        return this.toString() < otherTime.toString();
    }

    isAfter(otherTime: LocalTime): boolean {
        return this.toString() > otherTime.toString();
    }
    plus(secondsToAdd: number): LocalTime {
        const currentDateTime = new Date();
        currentDateTime.setHours(this.hour);
        currentDateTime.setMinutes(this.minute);
        currentDateTime.setSeconds(this.second);

        currentDateTime.setSeconds(currentDateTime.getSeconds() + secondsToAdd);

        return new LocalTime(
            currentDateTime.getHours(),
            currentDateTime.getMinutes(),
            currentDateTime.getSeconds(),
            currentDateTime.getMilliseconds()
        );
    }

    minus(secondsToSubtract: number): LocalTime {
        return this.plus(-secondsToSubtract);
    }

    toMillis(): number {
        const millisInHour = this.hour * 60 * 60 * 1000;
        const millisInMinute = this.minute * 60 * 1000;
        const millisInSecond = this.second * 1000;
        return millisInHour + millisInMinute + millisInSecond + this.milliseconds;
      }

    static now(): LocalTime {
        const currentDate = new Date();
        return new LocalTime(currentDate.getHours(), currentDate.getMinutes(), currentDate.getSeconds(), currentDate.getMilliseconds());
    }
}

export default LocalTime;