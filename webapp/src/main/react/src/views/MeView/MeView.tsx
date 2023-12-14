import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './MeView.css';
import { Company, Human } from '@nirahtech/erp';

interface Properties {
    human: Human|null;
    companies: Array<Company>;
    onSelectCompany: Function;
}

function MeView({human, companies}: Properties) {
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

    return (
        <section className='Me-Component' id='home'>
            <h1>{meAsHuman.firstName} {meAsHuman.lastName}</h1>
        </section>
    );
}

export default MeView;
