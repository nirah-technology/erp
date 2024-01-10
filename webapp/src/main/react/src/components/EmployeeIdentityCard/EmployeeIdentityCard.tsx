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
            <h2>{employee.human.firstName} {employee.human.lastName}</h2>
            <p>{employee.jobTitle}</p>
        </div>
        <div className='profile-details'>
            <h3>Informations Professionnelles</h3>
            <table>
                <tbody>
                    <tr>
                        <th>First Name:</th>
                        <td>{employee.human.firstName}</td>
                    </tr>
                    <tr>
                        <th>Last Name:</th>
                        <td>{employee.human.lastName}</td>
                    </tr>
                    <tr>
                        <th>Birth Date (age):</th>
                        <td>{DateTimeHelper.formatDate(employee.human.birthDate)} ({computeAge(employee.human.birthDate)} ans)</td>
                    </tr>
                    <tr>
                        <th>Gender:</th>
                        <td>{employee.human.gender}</td>
                    </tr>
                    <tr>
                        <th>Email:</th>
                        {(employee.businessEmailAddress.isPresent() ? (
                            <td><a href={"mailto:"+employee.businessEmailAddress.get().toString()}>{employee.businessEmailAddress.get().toString()}</a></td>
                        ) : (
                            <td><a href={"mailto:"+employee.human.emailAddress.toString()}>{employee.human.emailAddress.toString()}</a></td>
                        ))}
                    </tr>
                    <tr>
                        <th>Date d'embauche:</th>
                        <td>{DateTimeHelper.formatDate(employee.hiringDate)}</td>
                    </tr>
                    <tr>
                        <th>Addresse:</th>
                        {(employee.businessMailingAddress.isPresent() ? (
                            <td>{employee.businessMailingAddress.get().address}</td>
                            ) : (
                            <td>{employee.human.mailingAddress.address}</td>
                        ))}
                    </tr>
                    <tr>
                        <th>Téléphone:</th>
                        {(employee.businessPhoneNumber.isPresent() ? (
                            <td>{employee.businessPhoneNumber.get().toString()}</td>
                            ) : (
                            <td>{employee.human.phoneNumber.toString()}</td>
                        ))}
                    </tr>
                    <tr>
                        <th>Poste:</th>
                        <td>{employee.jobTitle}</td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>
  );
}

export default EmployeeIdentityCard;