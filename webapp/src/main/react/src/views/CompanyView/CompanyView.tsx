import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './CompanyView.css';
import { Company, Employee } from '@nirahtech/erp';

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
            <h1>{myCompany.getName()}</h1>

        </section>
    );
}

export default CompanyView;
