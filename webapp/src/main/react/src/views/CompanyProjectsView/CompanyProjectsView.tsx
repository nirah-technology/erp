import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './CompanyProjectsView.css';
import Company from '../../data/Company';
import Employee from '../../data/Employee';
import EmployeeIdentityCard from '../../components/EmployeeIdentityCard/EmployeeIdentityCard';
import CompanyIdentityCard from '../../components/CompanyIdentityCard/CompanyIdentityCard';

interface Properties {
    company: Company|null;
}

function CompanyProjectsView({company}: Properties) {
    const [myCompany, setMyCompany] = useState<Company>();
    const navigate = useNavigate();

    useEffect(() => {
        if (!company) {
            navigate("/");
        } else {
            setMyCompany(company);
        }
    }, []);

    if (!myCompany) {
        navigate("/");
        return (null);
    }
    return (
        <section className='CompanyProjectsView-Component' id='home'>
            <h1>Projets</h1>
            <ul>
                {Array.from(myCompany.getProjectsRegistry().getProjects()).map((project) => (
                    <li>
                        <h2>{project.getName()}</h2>
                    </li>
                ))}
            </ul>

        </section>
    );
}

export default CompanyProjectsView;
