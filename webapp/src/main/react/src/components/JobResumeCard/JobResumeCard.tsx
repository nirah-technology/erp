import React from 'react';
import { Link } from 'react-router-dom';
import Company from '../../data/Company';
import Employee from '../../data/Employee';
import Human from '../../data/Human';
import './JobResumeCard.css';

interface Properties {
    employee: Employee;
    company: Company;
    onSelectCompany: Function;
}

function JobResumeCard({ employee, company, onSelectCompany }: Properties) {

    const onLinkClickedHandler = (event: any, company: Company) => {
        if (onSelectCompany) {
            onSelectCompany(company);
        }
    }
    return (
        <div className='JobResumeCard-Component'>
            <h2>{company.getName()}</h2>
            <p>{employee.getJobTitle()}</p>
            <Link className='Button' to={"/work"} onClick={(e) => onLinkClickedHandler(e, company)}>
                Sélectionner
            </Link>
        </div>
    );
}

export default JobResumeCard;