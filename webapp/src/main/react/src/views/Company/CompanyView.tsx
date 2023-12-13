import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './CompanyView.css';
import Company from '../../data/Company';
import Employee from '../../data/Employee';
import EmployeeIdentityCard from '../../components/EmployeeIdentityCard/EmployeeIdentityCard';
import CompanyIdentityCard from '../../components/CompanyIdentityCard/CompanyIdentityCard';

interface Properties {
    company: Company|null;
    employee: Employee|null;
}

function CompanyView({company, employee}: Properties) {
    const [myCompany, setMyCompany] = useState<Company>();
    const [meAsEmployee, setMeAsEmployee] = useState<Employee>();
    const navigate = useNavigate();

    useEffect(() => {
        if (!company || !employee) {
            navigate("/");
        } else {
            setMeAsEmployee(employee);
            setMyCompany(company);
        }
    }, []);

    if (!myCompany || !meAsEmployee) {
        navigate("/");
        return (null);
    }
    return (
        <section className='Home-Component' id='home'>
            <CompanyIdentityCard company={myCompany} />
            <hr />
            <EmployeeIdentityCard employee={meAsEmployee} />
            <hr />
            <Link className='Button' to={"working-time"}>See My WorkTime</Link>

        </section>
    );
}

export default CompanyView;
