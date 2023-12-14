import React from 'react';
import './EmployeeIdentityCard.css';
import { LocalDate } from '@nirahtech/datetime';
import { DateTimeHelper, Employee } from '@nirahtech/erp';

interface Properties {
    employee: Employee
}

function EmployeeIdentityCard({employee}: Properties) {
    const computeAge = (date: LocalDate): number => {
        return LocalDate.now().getYear() - date.getYear();
    }
  
  return (
    <div className='EmployeeIdentityCard-Component'>
        <div className='profile-resume'>
            <img src="/logo512.png" alt="Avatar" />
            <h2>{employee.firstName} {employee.lastName}</h2>
            <p>{employee.getJobTitle()}</p>
        </div>
        <div className='profile-details'>
            <h3>Informations Professionnelles</h3>
            <table>
                <tbody>
                    <tr>
                        <th>First Name:</th>
                        <td>{employee.firstName}</td>
                    </tr>
                    <tr>
                        <th>Last Name:</th>
                        <td>{employee.lastName}</td>
                    </tr>
                    <tr>
                        <th>Birth Date (age):</th>
                        <td>{DateTimeHelper.formatDate(employee.birthDate)} ({computeAge(employee.birthDate)} ans)</td>
                    </tr>
                    <tr>
                        <th>Gender:</th>
                        <td>{employee.gender}</td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        <td><a href={"mailto:"+employee.getEmailAddress().toString()}>{employee.getEmailAddress().toString()}</a></td>
                    </tr>
                    <tr>
                        <th>Date d'embauche:</th>
                        <td>{DateTimeHelper.formatDate(employee.getHiringDate())}</td>
                    </tr>
                    <tr>
                        <th>Addresse:</th>
                        <td>{employee.getMailingAddress().address}</td>
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