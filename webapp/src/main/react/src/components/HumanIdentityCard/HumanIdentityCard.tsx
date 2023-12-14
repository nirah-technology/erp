import React from 'react';
import './HumanIdentityCard.css';
import { LocalDate, LocalDateTime } from '@nirahtech/datetime';
import { DateTimeHelper, Human } from '@nirahtech/erp';

interface Properties {
    human: Human
}

function HumanIdentityCard({human}: Properties) {

    const computeAge = (birthDate: LocalDate): number => {
        return LocalDateTime.now().getYear() - birthDate.getYear();
    }
  
  return (
    <div className='HumanIdentityCard-Component'>
        <div className='profile-resume'>
            <img src="/logo512.png" alt="Avatar" />
            <h2>{human.firstName} {human.lastName}</h2>
        </div>
        <div className='profile-details'>
            <h3>Informations Personnelles</h3>
            <table>
                <tbody>
                    <tr>
                        <th>First Name:</th>
                        <td>{human.firstName}</td>
                    </tr>
                    <tr>
                        <th>Last Name:</th>
                        <td>{human.lastName}</td>
                    </tr>
                    <tr>
                        <th>Birth Date (age):</th>
                        <td>{DateTimeHelper.formatDate(human.birthDate)} ({computeAge(human.birthDate)} ans)</td>
                    </tr>
                    <tr>
                        <th>Gender:</th>
                        <td>{human.gender}</td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>
  );
}

export default HumanIdentityCard;