import React from 'react';
import './WorkTimeWeekSheet.css';
import { Duration, LocalDateTime, TimeUnit } from '@nirahtech/datetime';
import { DateTimeHelper, Imputation, ImputationHelper } from '@nirahtech/erp';

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
        let finishDate: LocalDateTime = imputation.date;
        switch (imputation.timeUnit) {
            case TimeUnit.MINUTES:
                finishDate = imputation.date.plusDuration(Duration.ofMinutes(imputation.number));
                break;
            case TimeUnit.HOURS:
                finishDate = imputation.date.plusDuration(Duration.ofHours(imputation.number));
                break;
            case TimeUnit.DAYS:
                finishDate = imputation.date.plusDuration(Duration.ofDays(imputation.number));
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
            switch (imputation.timeUnit) {
                case TimeUnit.MINUTES:
                    workedMinutes += imputation.number;
                    break;
                case TimeUnit.HOURS:
                    workedMinutes += (imputation.number*60);
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
                        sortDatesDescending(DateTimeHelper.getDaysOfWeekForWeekNumber(weekNumber, LocalDateTime.now().getYear())).map((dayOfWeek) => {
                            return (
                                <tr>
                                    <th>{DateTimeHelper.getDayName(dayOfWeek.toLocalDate())} {DateTimeHelper.formatDate(dayOfWeek.toLocalDate())}</th>
                                    <td>
                                        <ul>
                                            <li>
                                                {
                                                    ImputationHelper.filterImputationsByDate(imputations, dayOfWeek.toLocalDate()).map((imputation) => (
                                                        <div>from: <span>{DateTimeHelper.formatTime(imputation.date.toLocalTime())}</span> to: <span>{DateTimeHelper.formatTime(computeFinishImputationDateTime(imputation).toLocalTime())}</span></div>
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