import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './CompanyView.css';
import Company from '../../data/Company';
import Employee from '../../data/Employee';

interface Properties {
    company: Company;
    employee: Employee;
}

function CompanyView({company, employee}: Properties) {
    const [myCompany] = useState<Company>(company);
    const [me] = useState<Employee>(employee);
    
    return (
        <section className='Home-Component' id='home'>
            <div className='identity'>
                <h1>{myCompany.getName()}</h1>
                <p>Date de création: {myCompany.getCreationDate().toString()}</p>
                <p>Téléphone: {myCompany.getPhoneNumber().toString()}</p>
                <p>Salariés: {myCompany.getEmployees().size}</p>
                <p>SIRET: {String(myCompany.getSiret().getCode())}</p>
                <p>SIREN: {String(myCompany.getSiren().getCode())}</p>
                <p>Adresse: {String(myCompany.getMailingAddress().getAddress())}</p>
            </div>
            <div className='identity'>
                <h1>{myCompany.getName()}</h1>
                <p>Date de création: {myCompany.getCreationDate().toString()}</p>
                <p>Téléphone: {myCompany.getPhoneNumber().toString()}</p>
                <p>Salariés: {myCompany.getEmployees().size}</p>
                <p>SIRET: {String(myCompany.getSiret().getCode())}</p>
                <p>SIREN: {String(myCompany.getSiren().getCode())}</p>
                <p>Adresse: {String(myCompany.getMailingAddress().getAddress())}</p>
            </div>
            <div>
                <h2>Temps travaillé</h2>
                <ul>
                    <li>
                        <h3>Aujourd'hui</h3>
                        <p>0 heures et 0 minutes</p>
                    </li>
                    <li>
                        <h3>Cette semaine</h3>
                        <p>0 heures et 0 minutes</p>
                    </li>
                    <li>
                        <h3>Ce mois-ci</h3>
                        <p>0 heures et 0 minutes</p>
                    </li>
                    <li>
                        <h3>Cette année</h3>
                        <p>0 heures et 0 minutes</p>
                    </li>
                </ul>
                <Link to={"/"}>Voir les détails</Link>
                <button type="button">Ajouter une imputation</button>
            </div>

        </section>
    );
}

export default CompanyView;
