import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './MeView.css';
import Human from '../../data/Human';
import Company from '../../data/Company';
import HumanIdentityCard from '../../components/HumanIdentityCard/HumanIdentityCard';
import JobResumeCard from '../../components/JobResumeCard/JobResumeCard';
import Employee from '../../data/Employee';

interface Properties {
    human: Human|null;
    companies: Array<Company>;
    onSelectCompany: Function;
}

function MeView({human, companies, onSelectCompany}: Properties) {
    const [meAsHuman, setMeAsHuman] = useState<Human>();
    const [myCompanies] = useState<Array<Company>>(companies);
    const navigate = useNavigate();

    useEffect(() => {
        if (!human) {
            navigate("/");
        } else {
            setMeAsHuman(human);
        }
    }, []);
    
    
    if (!meAsHuman) {
        navigate("/");
        return (null);
    }

    const retrieveMyselfAsEmployee = (company: Company): Employee|null => {
        let myselfAsEmployee: Employee|null = null;
        company.getEmployees().forEach((employee) => {
            if (employee.is(meAsHuman)) {
                myselfAsEmployee = employee;
            }
        })
        return myselfAsEmployee;
    }

    return (
        <section className='Me-Component' id='home'>
            <h1>Mon Identité</h1>
            <HumanIdentityCard human={meAsHuman} />
            <div>
                <h2>Mes entreprises</h2>
                <ul>
                    {
                        myCompanies.map(company => {
                            const meAsEmployee: Employee|null = retrieveMyselfAsEmployee(company);
                            if (meAsEmployee) {
                                return (
                                    <li>
                                        <JobResumeCard employee={meAsEmployee} company={company} onSelectCompany={onSelectCompany} />
                                    </li>);
                            }
                            return (null);
                        })

                    }
                </ul>
            </div>
        </section>
    );
}

export default MeView;
