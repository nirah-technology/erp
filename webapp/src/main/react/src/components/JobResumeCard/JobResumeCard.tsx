import React from 'react';
import { Link } from 'react-router-dom';
import './JobResumeCard.css';
import { Company, Employee } from '@nirahtech/erp';

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
            <p>{employee.jobTitle}</p>
            <Link className='Button' to={"/work"} onClick={(e) => onLinkClickedHandler(e, company)}>
                Sélectionner
            </Link>
        </div>
    );
}

export default JobResumeCard;