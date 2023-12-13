import React from 'react';
import Human from '../../data/Human';
import './HumanIdentityCard.css';

interface Properties {
    human: Human
}

function HumanIdentityCard({human}: Properties) {

    const formatBirthDate = (birthDate: Date): string => {
        const day = String(birthDate.getDate()).padStart(2, '0');
        const month = String(birthDate.getMonth() + 1).padStart(2, '0');
        const year = birthDate.getFullYear();
        return `${day}-${month}-${year}`;
    }

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
                        <td>{formatBirthDate(human.getBirthDate())} ({computeAge(human.getBirthDate())} ans)</td>
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