import React from 'react';
import Company from '../../data/Company';
import Employee from '../../data/Employee';
import Human from '../../data/Human';
import LocalDate from '../../data/LocalDate';
import { DateTimeHelper } from '../../data/Util';
import './CompanyIdentityCard.css';

interface Properties {
    company: Company
}

function CompanyIdentityCard({company}: Properties) {

    const computeAge = (birthDate: LocalDate): number => {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
  
  return (
    <div className='CompanyIdentityCard-Component'>
        <div className='profile-resume'>
            <img src="/logo512.png" alt="Avatar" />
            <h2>{company.getName()}</h2>
        </div>
        <div className='profile-details'>
            <h3>Profile Entreprise</h3>
            <table>
                <tbody>
                    <tr>
                        <th>Raison Sociale:</th>
                        <td>{company.getName()}</td>
                    </tr>
                    <tr>
                        <th>Date Création (age):</th>
                        <td>{DateTimeHelper.formatDate(company.getCreationDate())} ({computeAge(company.getCreationDate())})</td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td><a href={"mailto:"+company.getEmailAddress().toString()}>{company.getEmailAddress().toString()}</a></td>
                    </tr>
                    <tr>
                        <th>Addresse:</th>
                        <td>{company.getMailingAddress().getAddress()}</td>
                    </tr>
                    <tr>
                        <th>Téléphone:</th>
                        <td><a href={"tel:"+company.getPhoneNumber().toString()}>{company.getPhoneNumber().toString()}</a></td>
                    </tr>
                    <tr>
                        <th>SIREN:</th>
                        <td>{company.getSiren().getCode()}</td>
                    </tr>
                    <tr>
                        <th>SIRET:</th>
                        <td>{company.getSiret().getCode()}</td>
                    </tr>
                    <tr>
                        <th>Total Employés:</th>
                        <td>{company.getEmployees().length}</td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>
  );
}

export default CompanyIdentityCard;