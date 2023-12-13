import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './WorkingTimeView.css';
import Human from '../../data/Human';
import Company from '../../data/Company';
import HumanIdentityCard from '../../components/HumanIdentityCard/HumanIdentityCard';
import JobResumeCard from '../../components/JobResumeCard/JobResumeCard';
import Employee from '../../data/Employee';
import WorkTimeWeekSheet from '../../components/WorkTimeWeekSheet/WorkTimeWeekSheet';

interface Properties {
    employee: Employee|null;
    company: Company|null;
}

function WorkingTimeView({employee, company}: Properties) {
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
    
    
    if (!meAsEmployee || !myCompany) {
        navigate("/");
        return (null);
    }


    return (
        <section className='WorkingTimeView-Component' id='home'>
            <h1>Mon Temps de Travail</h1>
            <h2>Cette Semaine</h2>
            <WorkTimeWeekSheet />
            <hr />
            <h2>La Semaine Dernière</h2>
            <p>Semaine n°24</p>
            <WorkTimeWeekSheet />
            <hr />
            <h2>Ce Mois-çi</h2>
            <hr />
            <h2>Le Mois Dernier</h2>
            <hr />
        </section>
    );
}

export default WorkingTimeView;
