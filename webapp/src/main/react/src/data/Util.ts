import ChronoUnit from "./ChronoUnit";
import Duration from "./Duration";
import Imputation from "./Imputation";
import LocalDate from "./LocalDate";
import LocalDateTime from "./LocalDateTime";
import LocalTime from "./LocalTime";
import Week from "./Week";
import WorkTimeSheet from "./WorkTimeSheet";

export class ImputationHelper {
    public static getImputationsForWeekFromDay(workTimeSheet: WorkTimeSheet, day: LocalDate): Set<Imputation> {
        return new Set(workTimeSheet.listImputations().filter((imputation) => DateTimeHelper.isDateInCurrentWeek(imputation.getDate().toLocalDate())));
    }

    public static filterImputationsByDate (imputations: Set<Imputation>, day: LocalDate): Array<Imputation> {
        return Array.from(imputations).filter((imputation) => DateTimeHelper.haveSameDate(imputation.getDate().toLocalDate(), day));
    }
}

export class DateTimeHelper {

    public static getWeekNumber(date: LocalDate): number {
        const firstDayOfYear = new LocalDate(date.getYear(), 1, 1);
        const daysSinceStartOfYear = date.toEpochDay() - firstDayOfYear.toEpochDay();
        const weekNumber = Math.ceil((daysSinceStartOfYear + firstDayOfYear.getDayOfWeek()) / 7);
        return weekNumber;
    }

    public static getPreviousWeekNumber(date: LocalDate): number {
        const currentDate: LocalDate = date || LocalDate.now();
        const previousWeekDate: LocalDate = currentDate.minus(Duration.ofDays(7));
        return DateTimeHelper.getWeekNumber(previousWeekDate);
    }

    public static getFirstDayOfCurrentWeek(date: LocalDate): LocalDate {
        const currentDate: LocalDate = date || LocalDate.now();
        const currentDayOfWeek: number = currentDate.getDay();

        // Soustraction du nombre de jours pour obtenir la date du premier jour de la semaine
        const firstDayOfWeek: LocalDate = currentDate.minus(Duration.ofDays(currentDayOfWeek));
        return firstDayOfWeek;
    }

    public static getLastDayOfCurrentWeek(date: LocalDate): LocalDate {
        const firstDayOfWeek: LocalDate = DateTimeHelper.getFirstDayOfCurrentWeek(date);
        const lastDayOfWeek: LocalDate = firstDayOfWeek.plus(Duration.ofDays(6)); // Ajout de six jours
        return lastDayOfWeek;
    }

    public static isDateInCurrentWeek(dateToCheck: LocalDate): boolean {
        const currentDate: LocalDate = LocalDate.now();
        const firstDayOfWeek: LocalDate = DateTimeHelper.getFirstDayOfCurrentWeek(currentDate);
        const lastDayOfWeek: LocalDate = DateTimeHelper.getLastDayOfCurrentWeek(currentDate);
        return dateToCheck >= firstDayOfWeek && dateToCheck <= lastDayOfWeek;
    }

    public static getDaysOfWeekForWeekNumber(weekNumber: number, year: number): LocalDateTime[] {
        const januaryFirst: LocalDate = new LocalDate(year, 0, 1);
        const firstDayOfYear: number = januaryFirst.getDay(); // Jour de la semaine du 1er janvier
    
        const daysToFirstSaturday: number = 6 - firstDayOfYear; // Nombre de jours jusqu'au premier samedi
        let daysToTargetWeek: number = (weekNumber - 1) * 7 + daysToFirstSaturday; // Nombre de jours jusqu'à la semaine cible
    
        const targetWeekStartDate: LocalDateTime = new LocalDateTime(year, 0, 1 + daysToTargetWeek, 0,0,0,0); // Date de début de la semaine cible
    
        let daysOfWeek: LocalDateTime[] = [];
        let currentYear = year;
    
        for (let i = 0; i < 7; i++) {
            let day = targetWeekStartDate.plus(Duration.ofDays(1));
            
            if (day.getYear() !== currentYear) {
                currentYear = day.getYear();
                daysToTargetWeek -= 7;
                day = day.minus(Duration.ofDays(7));
            }
            daysOfWeek.push(day);
        }
        return daysOfWeek;
    }

    public static getDayName(date: LocalDate): string {
        return Week[date.getDayOfWeek()];
    }

    public static formatDate(date: LocalDate): string {
        return `${date.getDay()}/${date.getMonth()}/${date.getYear()}`;
    }

    public static formatTime(date: LocalTime): string {
        return `${date.getHour()}:${date.getMinute()}`;
    }

    public static haveSameDate (date1: LocalDate, date2: LocalDate): boolean {
        return (
            date1.isEqual(date2)
        );
    }

    public static differenceInMinutes(date1: LocalDateTime, date2: LocalDateTime): number {
        const differenceInMilliseconds: number = ChronoUnit.MINUTES.between(date1, date2);
        return Math.floor(differenceInMilliseconds / (1000 * 60));
    }
    
}