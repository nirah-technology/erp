import React from 'react';
import './WorkTimeWeekSheet.css';
import Employee from '../../data/Employee';
import Imputation from '../../data/Imputation';

interface Properties {
    weekNumber: number;
    imputations: Set<Imputation>
}

function WorkTimeWeekSheet({ weekNumber, imputations }: Properties) {
    const getDayName = (date: Date): string => {
        return date.toLocaleDateString('default', { weekday: 'long' });
    }

    const formatDate = (date: Date): string => {
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

    const formatTime = (date: Date): string => {
        const hours: number = date.getHours();
        const minutes: number = date.getMinutes();
        const formattedHours: string = hours.toString().padStart(2, '0');
        const formattedMinutes: string = minutes.toString().padStart(2, '0');
        return `${formattedHours}:${formattedMinutes}`;
    }

    const sortDatesDescending = (dates: Date[]): Date[] => {
        return dates.sort((a, b) => b.getTime() - a.getTime());
    }
    
    
    const getDaysOfWeekByNumber = (weekNumber: number, year: number): Date[] => {
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
        return sortDatesDescending(daysOfWeek);
    }

    const haveSameDate = (date1: Date, date2: Date): boolean => {
        return (
            date1.getFullYear() === date2.getFullYear() &&
            date1.getMonth() === date2.getMonth() &&
            date1.getDate() === date2.getDate()
        );
    }
    

    const getImputationsByDate = (day: Date): Array<Imputation> => {
        return Array.from(imputations).filter((imputation) => haveSameDate(imputation.getDate(), day));
    }

    const computeFinishImputationDateTime = (imputation: Imputation): Date => {
        return new Date();
    }

    const computeWorkedHours = (date: Date): number => {
        const imputationsByDate: Array<Imputation> = getImputationsByDate(date);
        let workedHours: number = 0;
        return workedHours;
    }
    
    return (
        <div className='WorkTimeWeekSheet-Component'>
            <table>
                <thead>
                    <tr>
                        <th>Jour</th>
                        <th>Temps Travaillé</th>
                        <th>Total Heures Travaillé</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        getDaysOfWeekByNumber(weekNumber, new Date().getFullYear()).map((dayOfWeek) => {
                            return (
                                <tr>
                                    <th>{getDayName(dayOfWeek)} {formatDate(dayOfWeek)}</th>
                                    <td>
                                        <ul>
                                            <li>
                                                {
                                                    getImputationsByDate(dayOfWeek).map((imputation) => (
                                                        <div>from: <span>{formatTime(imputation.getDate())}</span> to: <span>{formatTime(computeFinishImputationDateTime(imputation))}</span></div>
                                                    ))
                                                }
                                            </li>
                                        </ul>
                                    </td>
                                    <td>{computeWorkedHours(dayOfWeek)}</td>
                                </tr>
                            );
                        })
                    }
                </tbody>
            </table>
        </div>
    );
}

export default WorkTimeWeekSheet;