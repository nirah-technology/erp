import React from 'react';
import Employee from '../../data/Employee';
import Human from '../../data/Human';
import './EmployeeIdentityCard.css';

interface Properties {
    employee: Employee
}

function EmployeeIdentityCard({employee}: Properties) {

    const formatDate = (date: Date): string => {
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}-${month}-${year}`;
    }

    const computeAge = (date: Date): number => {
        return (new Date()).getFullYear() - date.getFullYear();
    }
  
  return (
    <div className='EmployeeIdentityCard-Component'>
        <div className='profile-resume'>
            <img src="/logo512.png" alt="Avatar" />
            <h2>{employee.getFirstName()} {employee.getLastName()}</h2>
            <p>{employee.getJobTitle()}</p>
        </div>
        <div className='profile-details'>
            <h3>Profile Employé</h3>
            <table>
                <tbody>
                    <tr>
                        <th>First Name:</th>
                        <td>{employee.getFirstName()}</td>
                    </tr>
                    <tr>
                        <th>Last Name:</th>
                        <td>{employee.getLastName()}</td>
                    </tr>
                    <tr>
                        <th>Birth Date (age):</th>
                        <td>{formatDate(employee.getBirthDate())} ({computeAge(employee.getBirthDate())} ans)</td>
                    </tr>
                    <tr>
                        <th>Gender:</th>
                        <td>{employee.getGender()}</td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td><a href={"mailto:"+employee.getEmailAddress().toString()}>{employee.getEmailAddress().toString()}</a></td>
                    </tr>
                    <tr>
                        <th>Date d'embauche:</th>
                        <td>{formatDate(employee.getHiringDate())}</td>
                    </tr>
                    <tr>
                        <th>Addresse:</th>
                        <td>{employee.getMailingAddress().getAddress()}</td>
                    </tr>
                    <tr>
                        <th>Téléphone:</th>
                        <td><a href={"tel:"+employee.getPhoneNumber().toString()}>{employee.getPhoneNumber().toString()}</a></td>
                    </tr>
                    <tr>
                        <th>Poste:</th>
                        <td>{employee.getJobTitle()}</td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>
  );
}

export default EmployeeIdentityCard;