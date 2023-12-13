import React from 'react';
import './WorkTimeWeekSheet.css';
import Employee from '../../data/Employee';
import Imputation from '../../data/Imputation';
import TimeUnit from '../../data/TimeUnit';
import { DateTimeHelper, ImputationHelper } from '../../data/Util';
import LocalDate from '../../data/LocalDate';
import LocalDateTime from '../../data/LocalDateTime';
import Duration from '../../data/Duration';

interface Properties {
    weekNumber: number;
    imputations: Set<Imputation>
}

function WorkTimeWeekSheet({ weekNumber, imputations }: Properties) {
    

    const sortDatesDescending = (dates: LocalDateTime[]): LocalDateTime[] => {
        dates.sort((a, b) => {
          if (a.isAfter(b)) {
            return -1;
          } else if (a.isBefore(b)) {
            return 1;
          }
          return 0;
        });
        return dates;
      };
    
    const computeFinishImputationDateTime = (imputation: Imputation): LocalDateTime => {
        let finishDate: LocalDateTime = imputation.getDate();
        switch (imputation.getTimeUnit()) {
            case TimeUnit.MINUTES:
                finishDate = imputation.getDate().plus(Duration.ofMinutes(imputation.getNumber()));
                break;
            case TimeUnit.HOURS:
                finishDate = imputation.getDate().plus(Duration.ofHours(imputation.getNumber()));
                break;
            case TimeUnit.DAYS:
                finishDate = imputation.getDate().plus(Duration.ofDays(imputation.getNumber()));
                break;
            default:
                break;
        }
        return finishDate;
    }

    const computeWorkedMinutes = (date: LocalDateTime): number => {
        const imputationsByDate: Array<Imputation> = ImputationHelper.filterImputationsByDate(imputations, date.toLocalDate());
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
                                    <th>{DateTimeHelper.getDayName(dayOfWeek.toLocalDate())} {DateTimeHelper.formatDate(dayOfWeek.toLocalDate())}</th>
                                    <td>
                                        <ul>
                                            <li>
                                                {
                                                    ImputationHelper.filterImputationsByDate(imputations, dayOfWeek.toLocalDate()).map((imputation) => (
                                                        <div>from: <span>{DateTimeHelper.formatTime(imputation.getDate().toLocalTime())}</span> to: <span>{DateTimeHelper.formatTime(computeFinishImputationDateTime(imputation).toLocalTime())}</span></div>
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