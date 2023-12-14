import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './HumanJobsView.css';
import JobResumeCard from '../../components/JobResumeCard/JobResumeCard';
import { Company, Employee, Human } from '@nirahtech/erp';

interface Properties {
    human: Human | null;
    companies: Array<Company>;
    onSelectCompany: Function;
}

function HumanJobsView({ human, companies, onSelectCompany }: Properties) {
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

    const retrieveMyselfAsEmployee = (company: Company): Employee | null => {
        let myselfAsEmployee: Employee | null = null;
        company.getEmployees().forEach((employee) => {
            if (employee.is(meAsHuman)) {
                myselfAsEmployee = employee;
            }
        })
        return myselfAsEmployee;
    }

    return (
        <section className='Me-Component' id='home'>
            <h1>Mes Jobs</h1>
            <ul>
                {
                    myCompanies.map(company => {
                        const meAsEmployee: Employee | null = retrieveMyselfAsEmployee(company);
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
            <button type='button'>Join New Company</button>
        </section>
    );
}

export default HumanJobsView;
