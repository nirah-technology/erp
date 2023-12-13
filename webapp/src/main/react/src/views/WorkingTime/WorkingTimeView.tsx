import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './WorkingTimeView.css';
import Human from '../../data/Human';
import Company from '../../data/Company';
import HumanIdentityCard from '../../components/HumanIdentityCard/HumanIdentityCard';
import JobResumeCard from '../../components/JobResumeCard/JobResumeCard';
import Employee from '../../data/Employee';
import WorkTimeWeekSheet from '../../components/WorkTimeWeekSheet/WorkTimeWeekSheet';
import Imputation from '../../data/Imputation';
import WorkTimeSheet from '../../data/WorkTimeSheet';

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

    const getWeekNumber = (date: Date): number => {
        const currentDate: Date = date || new Date();
        const firstDayOfYear: Date = new Date(currentDate.getFullYear(), 0, 1);
        const pastDaysOfYear: number = (currentDate.getTime() - firstDayOfYear.getTime()) / 86400000;
        return Math.ceil((pastDaysOfYear + firstDayOfYear.getDay() + 1) / 7);
    }

    const getPreviousWeekNumber = (date: Date): number => {
        const currentDate: Date = date || new Date();
        const previousWeekDate: Date = new Date(currentDate.getTime() - 7 * 24 * 60 * 60 * 1000); // Soustraction de 7 jours
        return getWeekNumber(previousWeekDate);
    }

    const getFirstDayOfCurrentWeek = (date: Date): Date => {
        const currentDate: Date = date || new Date();
        const currentDayOfWeek: number = currentDate.getDay();
    
        // Soustraction du nombre de jours pour obtenir la date du premier jour de la semaine
        const firstDayOfWeek: Date = new Date(currentDate.getTime() - currentDayOfWeek * 24 * 60 * 60 * 1000);
        return firstDayOfWeek;
    }

    const getLastDayOfCurrentWeek = (date: Date): Date => {
        const firstDayOfWeek: Date = getFirstDayOfCurrentWeek(date);
        const lastDayOfWeek: Date = new Date(firstDayOfWeek.getTime() + 6 * 24 * 60 * 60 * 1000); // Ajout de six jours
        return lastDayOfWeek;
    }

    const isDateInCurrentWeek = (dateToCheck: Date): boolean => {
        const currentDate: Date = new Date();
        const firstDayOfWeek: Date = getFirstDayOfCurrentWeek(currentDate);
        const lastDayOfWeek: Date = getLastDayOfCurrentWeek(currentDate);
        return dateToCheck >= firstDayOfWeek && dateToCheck <= lastDayOfWeek;
    }
    
    
    
    
    if (!meAsEmployee || !myCompany) {
        navigate("/");
        return (null);
    } else {
        
        const getImputationsForCurrentWeek = (): Set<Imputation> => {
            const workTimeSheet: WorkTimeSheet = meAsEmployee.getWorkTimeSheet();
            return new Set(workTimeSheet.listImputations().filter((imputation) => isDateInCurrentWeek(imputation.getDate())));
        };

        return (
            <section className='WorkingTimeView-Component' id='home'>
                <h1>Mon Temps de Travail</h1>
                <div>
                    <h2>Cette Semaine</h2>
                    <p>Semaine n°{getWeekNumber(new Date())-1}</p>
                    <WorkTimeWeekSheet weekNumber={getWeekNumber(new Date())-1} imputations={getImputationsForCurrentWeek()} />
                    <button type="button">Imputer du Temps</button>
                </div>
                <hr />
                <div>
                    <h2>La Semaine Dernière</h2>
                    <p>Semaine n°{getPreviousWeekNumber(new Date())-1}</p>
                    <WorkTimeWeekSheet weekNumber={getPreviousWeekNumber(new Date())-1} imputations={getImputationsForCurrentWeek()} />
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
            </section>
        );
    }
}

export default WorkingTimeView;
