import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './CompanyProfileView.css';
import CompanyIdentityCard from '../../components/CompanyIdentityCard/CompanyIdentityCard';
import { Company, Employee } from '@nirahtech/erp';

interface Properties {
    company: Company|null;
    employee: Employee|null;
}

function CompanyProfileView({company, employee}: Properties) {
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

        </section>
    );
}

export default CompanyProfileView;
