import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './WorkingTimeView.css';
import WorkTimeWeekSheet from '../../components/WorkTimeWeekSheet/WorkTimeWeekSheet';

import ImputationForm from '../../components/ImputationForm/ImputationForm';
import { LocalDateTime } from '@nirahtech/datetime';
import { Company, DateTimeHelper, Employee, Imputation, ImputationHelper } from '@nirahtech/erp';

interface Properties {
    employee: Employee | null;
    company: Company | null;
}

function WorkingTimeView({ employee, company }: Properties) {
    const [meAsEmployee, setMeAsEmployee] = useState<Employee>();
    const [myCompany, setMyCompany] = useState<Company>();
    const navigate = useNavigate();

    useEffect(() => {
        if (!employee || !company) {
            navigate("/");
        } else {
            setMeAsEmployee(employee);
            setMyCompany(company);
        }
    }, []);

    const onNewImputationHandler = (imputation: Imputation) => {

    }

    if (!meAsEmployee || !myCompany) {
        navigate("/");
        return (null);
    } else {
        
        const today: LocalDateTime = LocalDateTime.now();
        const weekNumber: number = DateTimeHelper.getWeekNumber(today.toLocalDate())-1;
        const previousWeekNumber: number = DateTimeHelper.getPreviousWeekNumber(today.toLocalDate())-1;
        const imputationsForCurrentWeek: Set<Imputation> = ImputationHelper.getImputationsForWeekFromDay(meAsEmployee.getWorkTimeSheet(), today.toLocalDate());
        const lastDayOfPreviousWeek: LocalDateTime = DateTimeHelper.getDaysOfWeekForWeekNumber(previousWeekNumber, today.getYear())[0];
        const imputationsForPreviousWeek: Set<Imputation> = ImputationHelper.getImputationsForWeekFromDay(meAsEmployee.getWorkTimeSheet(), lastDayOfPreviousWeek.toLocalDate());

        return (
            <section className='WorkingTimeView-Component' id='home'>
                <h1>Mon Temps de Travail</h1>
                <div>
                    <h2>Cette Semaine</h2>
                    <p>Semaine n°{weekNumber}</p>
                    <WorkTimeWeekSheet weekNumber={weekNumber} imputations={imputationsForCurrentWeek} />
                    <p>Temps travaillé: </p>
                    <button type="button">Imputer du Temps</button>
                </div>
                <hr />
                <div>
                    <h2>La Semaine Dernière</h2>
                    <p>Semaine n°{previousWeekNumber}</p>
                    <WorkTimeWeekSheet weekNumber={previousWeekNumber} imputations={imputationsForPreviousWeek} />
                    <p>Temps travaillé: </p>
                </div>
                <hr />
                <div>
                    <h2>Ce Mois-çi</h2>
                </div>
                <hr />
                <div>
                    <h2>Le Mois Dernier</h2>
                </div>
                <hr />
                <div>
                    <ImputationForm onNewImputation={onNewImputationHandler} company={myCompany} />
                </div>
            </section>
        );
    }
}

export default WorkingTimeView;
