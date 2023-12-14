import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './EmployeeProfileView.css';
import EmployeeIdentityCard from '../../components/EmployeeIdentityCard/EmployeeIdentityCard';
import { Employee } from '@nirahtech/erp';

interface Properties {
    employee: Employee|null;
}

function EmployeeProfileView({employee}: Properties) {
    const [meAsEmployee, setMeAsEmployee] = useState<Employee>();
    const navigate = useNavigate();

    useEffect(() => {
        if (!employee) {
            navigate("/");
        } else {
            setMeAsEmployee(employee);
        }
    }, []);
    
    
    if (!meAsEmployee) {
        navigate("/");
        return (null);
    }

    return (
        <section className='EmployeeProfileView-Component' id='home'>
            <h1>Mon Profil Professionnel</h1>
            <EmployeeIdentityCard employee={meAsEmployee} />
        </section>
    );
}

export default EmployeeProfileView;
