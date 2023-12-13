import Duration from "./Duration";
import Week from "./Week";

class LocalDate {
    private year: number;
    private month: number;
    private day: number;

    constructor(year: number, month: number, day: number) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    getYear(): number {
        return this.year;
    }

    getMonth(): number {
        return this.month;
    }

    getDay(): number {
        return this.day;
    }

    toString(): string {
        return `${this.year}-${this.month}-${this.day}`;
    }

    isEqual(otherDate: LocalDate): boolean {
        return this.toString() === otherDate.toString();
    }

    isBefore(otherDate: LocalDate): boolean {
        return this.toString() < otherDate.toString();
    }

    isAfter(otherDate: LocalDate): boolean {
        return this.toString() > otherDate.toString();
    }

    // Méthodes utilitaires
    isLeapYear(): boolean {
        return (this.year % 4 === 0 && this.year % 100 !== 0) || this.year % 400 === 0;
    }

    getDayOfWeek(): Week {
        const daysOfWeek = [
            Week.SUNDAY,
            Week.MONDAY,
            Week.TUESDAY,
            Week.WEDNESDAY,
            Week.THURSDAY,
            Week.FRIDAY,
            Week.SATURDAY
        ];
        const dayIndex = new Date(this.year, this.month - 1, this.day).getDay();
        return daysOfWeek[dayIndex];
    }

    plus(duration: Duration): LocalDate {
        const millisecondsPerDay = 24 * 60 * 60 * 1000;
        const daysToAdd = Math.floor(duration.getMilliseconds() / millisecondsPerDay);
        const date = new Date(this.year, this.month - 1, this.day); // month is 0-indexed in JavaScript Date

        date.setDate(date.getDate() + daysToAdd);
        return new LocalDate(date.getFullYear(), date.getMonth() + 1, date.getDate());
    }

    minus(duration: Duration): LocalDate {
        const millisecondsPerDay = 24 * 60 * 60 * 1000;
        const daysToSubtract = Math.floor(duration.getMilliseconds() / millisecondsPerDay);
        const date = new Date(this.year, this.month - 1, this.day);

        date.setDate(date.getDate() - daysToSubtract);
        return new LocalDate(date.getFullYear(), date.getMonth() + 1, date.getDate());
    }

    toMillis(): number {
        const UTCDate = new Date(Date.UTC(this.year, this.month - 1, this.day));
        return UTCDate.getTime();
    }
    toEpochDay(): number {
        const years = this.year;
        const months = this.month;
        const days = this.day;

        const a = Math.floor((14 - months) / 12);
        const y = years + 4800 - a;
        const m = months + 12 * a - 3;

        const julianDay = days +
            Math.floor((153 * m + 2) / 5) +
            365 * y +
            Math.floor(y / 4) -
            Math.floor(y / 100) +
            Math.floor(y / 400) -
            32045;

        return julianDay - 2440588;
    }
    static now(): LocalDate {
        const currentDate = new Date();
        return new LocalDate(currentDate.getFullYear(), currentDate.getMonth() + 1, currentDate.getDate());
    }
    static ofEpochDay(epochDay: number): LocalDate {
        const millisPerDay = 86400000; // Nombre de millisecondes dans une journée
        const epochMillis = epochDay * millisPerDay;
        const date = new Date(epochMillis);
    
        const year = date.getFullYear();
        const month = date.getMonth() + 1; // JavaScript utilise un index de mois débutant à 0
        const day = date.getDate();
    
        return new LocalDate(year, month, day);
      }

}
export default LocalDate;