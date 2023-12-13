import React from 'react';
import './WorkTimeWeekSheet.css';
import Employee from '../../data/Employee';
import Imputation from '../../data/Imputation';
import TimeUnit from '../../data/TimeUnit';
import { DateTimeHelper, ImputationHelper } from '../../data/Util';

interface Properties {
    weekNumber: number;
    imputations: Set<Imputation>
}

function WorkTimeWeekSheet({ weekNumber, imputations }: Properties) {
    

    const sortDatesDescending = (dates: Date[]): Date[] => {
        return dates.sort((a, b) => b.getTime() - a.getTime());
    }
    
    const computeFinishImputationDateTime = (imputation: Imputation): Date => {
        let finishDate = new Date(imputation.getDate());
        switch (imputation.getTimeUnit()) {
            case TimeUnit.MINUTES:
                finishDate.setMinutes(imputation.getDate().getMinutes()+imputation.getNumber());
                break;
            case TimeUnit.HOURS:
                finishDate.setHours(imputation.getDate().getHours()+imputation.getNumber());
                break;
            case TimeUnit.DAYS:
                finishDate.setDate(imputation.getDate().getDate()+imputation.getNumber());
                break;
            case TimeUnit.MONTHS:
                finishDate.setDate(imputation.getDate().getMonth()+imputation.getNumber());
                break;
            case TimeUnit.YEARS:
                finishDate.setDate(imputation.getDate().getFullYear()+imputation.getNumber());
                break;
            default:
                break;
        }
        return finishDate;
    }

    const computeWorkedMinutes = (date: Date): number => {
        const imputationsByDate: Array<Imputation> = ImputationHelper.filterImputationsByDate(imputations, date);
        let workedMinutes: number = 0;
        imputationsByDate.forEach((imputation) => {
            switch (imputation.getTimeUnit()) {
                case TimeUnit.MINUTES:
                    workedMinutes += imputation.getNumber();
                    break;
                case TimeUnit.HOURS:
                    workedMinutes += (imputation.getNumber()*60);
                    break;
                default:
                    break;
            }
        });
        let minutesLefts: number = workedMinutes/60;
        return minutesLefts;
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
                        sortDatesDescending(DateTimeHelper.getDaysOfWeekForWeekNumber(weekNumber, new Date().getFullYear())).map((dayOfWeek) => {
                            return (
                                <tr>
                                    <th>{DateTimeHelper.getDayName(dayOfWeek)} {DateTimeHelper.formatDate(dayOfWeek)}</th>
                                    <td>
                                        <ul>
                                            <li>
                                                {
                                                    ImputationHelper.filterImputationsByDate(imputations, dayOfWeek).map((imputation) => (
                                                        <div>from: <span>{DateTimeHelper.formatTime(imputation.getDate())}</span> to: <span>{DateTimeHelper.formatTime(computeFinishImputationDateTime(imputation))}</span></div>
                                                    ))
                                                }
                                            </li>
                                        </ul>
                                    </td>
                                    <td>{computeWorkedMinutes(dayOfWeek)}</td>
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