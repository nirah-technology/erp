import Imputation from "./Imputation";
import WorkTimeSheet from "./WorkTimeSheet";

export class ImputationHelper {
    public static getImputationsForWeekFromDay(workTimeSheet: WorkTimeSheet, day: Date): Set<Imputation> {
        return new Set(workTimeSheet.listImputations().filter((imputation) => DateTimeHelper.isDateInCurrentWeek(imputation.getDate())));
    }

    public static filterImputationsByDate (imputations: Set<Imputation>, day: Date): Array<Imputation> {
        return Array.from(imputations).filter((imputation) => DateTimeHelper.haveSameDate(imputation.getDate(), day));
    }
}

export class DateTimeHelper {

    public static getWeekNumber(date: Date): number {
        const currentDate: Date = date || new Date();
        const firstDayOfYear: Date = new Date(currentDate.getFullYear(), 0, 1);
        const pastDaysOfYear: number = (currentDate.getTime() - firstDayOfYear.getTime()) / 86400000;
        return Math.ceil((pastDaysOfYear + firstDayOfYear.getDay() + 1) / 7);
    }

    public static getPreviousWeekNumber(date: Date): number {
        const currentDate: Date = date || new Date();
        const previousWeekDate: Date = new Date(currentDate.getTime() - 7 * 24 * 60 * 60 * 1000); // Soustraction de 7 jours
        return DateTimeHelper.getWeekNumber(previousWeekDate);
    }

    public static getFirstDayOfCurrentWeek(date: Date): Date {
        const currentDate: Date = date || new Date();
        const currentDayOfWeek: number = currentDate.getDay();

        // Soustraction du nombre de jours pour obtenir la date du premier jour de la semaine
        const firstDayOfWeek: Date = new Date(currentDate.getTime() - currentDayOfWeek * 24 * 60 * 60 * 1000);
        return firstDayOfWeek;
    }

    public static getLastDayOfCurrentWeek(date: Date): Date {
        const firstDayOfWeek: Date = DateTimeHelper.getFirstDayOfCurrentWeek(date);
        const lastDayOfWeek: Date = new Date(firstDayOfWeek.getTime() + 6 * 24 * 60 * 60 * 1000); // Ajout de six jours
        return lastDayOfWeek;
    }

    public static isDateInCurrentWeek(dateToCheck: Date): boolean {
        const currentDate: Date = new Date();
        const firstDayOfWeek: Date = DateTimeHelper.getFirstDayOfCurrentWeek(currentDate);
        const lastDayOfWeek: Date = DateTimeHelper.getLastDayOfCurrentWeek(currentDate);
        return dateToCheck >= firstDayOfWeek && dateToCheck <= lastDayOfWeek;
    }

    public static getDaysOfWeekForWeekNumber(weekNumber: number, year: number): Date[] {
        const januaryFirst: Date = new Date(year, 0, 1);
        const firstDayOfYear: number = januaryFirst.getDay(); // Jour de la semaine du 1er janvier
    
        const daysToFirstSaturday: number = 6 - firstDayOfYear; // Nombre de jours jusqu'au premier samedi
        let daysToTargetWeek: number = (weekNumber - 1) * 7 + daysToFirstSaturday; // Nombre de jours jusqu'à la semaine cible
    
        const targetWeekStartDate: Date = new Date(year, 0, 1 + daysToTargetWeek); // Date de début de la semaine cible
    
        let daysOfWeek: Date[] = [];
        let currentYear = year;
    
        for (let i = 0; i < 7; i++) {
            const day: Date = new Date(targetWeekStartDate);
            day.setDate(targetWeekStartDate.getDate() + i);
            
            if (day.getFullYear() !== currentYear) {
                currentYear = day.getFullYear();
                daysToTargetWeek -= 7;
                day.setDate(day.getDate() - 7);
            }
            daysOfWeek.push(day);
        }
        return daysOfWeek;
    }

    public static getDayName(date: Date): string {
        return date.toLocaleDateString('default', { weekday: 'long' });
    }

    public static formatDate(date: Date): string {
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

    public static formatTime(date: Date): string {
        const hours: number = date.getHours();
        const minutes: number = date.getMinutes();
        const formattedHours: string = hours.toString().padStart(2, '0');
        const formattedMinutes: string = minutes.toString().padStart(2, '0');
        return `${formattedHours}:${formattedMinutes}`;
    }

    public static haveSameDate (date1: Date, date2: Date): boolean {
        return (
            date1.getFullYear() === date2.getFullYear() &&
            date1.getMonth() === date2.getMonth() &&
            date1.getDate() === date2.getDate()
        );
    }

    public static differenceInMinutes(date1: Date, date2: Date): number {
        const differenceInMilliseconds: number = Math.abs(date1.getTime() - date2.getTime());
        return Math.floor(differenceInMilliseconds / (1000 * 60));
    }
    
}