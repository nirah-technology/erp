import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './WorkingTimeView.css';
import Company from '../../data/Company';
import Employee from '../../data/Employee';
import WorkTimeWeekSheet from '../../components/WorkTimeWeekSheet/WorkTimeWeekSheet';
import Imputation from '../../data/Imputation';
import WorkTimeSheet from '../../data/WorkTimeSheet';
import { DateTimeHelper, ImputationHelper } from '../../data/Util';
import ImputationForm from '../../components/ImputationForm/ImputationForm';

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
        
        const today: Date = new Date();
        const weekNumber: number = DateTimeHelper.getWeekNumber(today)-1;
        const previousWeekNumber: number = DateTimeHelper.getPreviousWeekNumber(today)-1;
        const imputationsForCurrentWeek: Set<Imputation> = ImputationHelper.getImputationsForWeekFromDay(meAsEmployee.getWorkTimeSheet(), today);
        const lastDayOfPreviousWeek: Date = DateTimeHelper.getDaysOfWeekForWeekNumber(previousWeekNumber, today.getFullYear())[0];
        const imputationsForPreviousWeek: Set<Imputation> = ImputationHelper.getImputationsForWeekFromDay(meAsEmployee.getWorkTimeSheet(), lastDayOfPreviousWeek);

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
