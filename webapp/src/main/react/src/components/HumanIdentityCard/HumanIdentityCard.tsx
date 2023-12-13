import React from 'react';
import Human from '../../data/Human';
import './HumanIdentityCard.css';
import { DateTimeHelper } from '../../data/Util';

interface Properties {
    human: Human
}

function HumanIdentityCard({human}: Properties) {

    const computeAge = (birthDate: Date): number => {
        return (new Date()).getFullYear() - birthDate.getFullYear();
    }
  
  return (
    <div className='HumanIdentityCard-Component'>
        <div className='profile-resume'>
            <img src="/logo512.png" alt="Avatar" />
            <h2>{human.getFirstName()} {human.getLastName()}</h2>
        </div>
        <div className='profile-details'>
            <h3>Informations Personnelles</h3>
            <table>
                <tbody>
                    <tr>
                        <th>First Name:</th>
                        <td>{human.getFirstName()}</td>
                    </tr>
                    <tr>
                        <th>Last Name:</th>
                        <td>{human.getLastName()}</td>
                    </tr>
                    <tr>
                        <th>Birth Date (age):</th>
                        <td>{DateTimeHelper.formatDate(human.getBirthDate())} ({computeAge(human.getBirthDate())} ans)</td>
                    </tr>
                    <tr>
                        <th>Gender:</th>
                        <td>{human.getGender()}</td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>
  );
}

export default HumanIdentityCard;