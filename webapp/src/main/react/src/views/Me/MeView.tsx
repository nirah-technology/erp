import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './MeView.css';
import Human from '../../data/Human';
import Company from '../../data/Company';

interface Properties {
    human: Human;
    companies: Array<Company>;
    onSelectCompany: Function;
}

function MeView({human, companies, onSelectCompany}: Properties) {
    const [me] = useState<Human>(human);
    const [myCompanies] = useState<Array<Company>>(companies);
    
    const onLinkClickedHandler = (event: any, company: Company) => {
        if (onSelectCompany) {
            onSelectCompany(company);
        }
    }

    return (
        <section className='Home-Component' id='home'>
            <h1>{me.getFirstName()} {me.getLastName()}</h1>
            <p>Date de naissance: {me.getBirthDate().toString()}</p>
            <p>Genre: {me.getGender()}</p>
            <div>
                <h2>Mes entreprises</h2>
                {myCompanies.map(company => <li>
                    <Link to={"/my-companies/".concat(String(company.getSiren().getCode()))} onClick={(e) => onLinkClickedHandler(e, company)}>
                        {company.getName()}
                    </Link>
                </li>)}
            </div>
        </section>
    );
}

export default MeView;
