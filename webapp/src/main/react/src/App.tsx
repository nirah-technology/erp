import { useEffect, useState } from 'react';

import { Route, Routes } from "react-router-dom";


import './App.css';
import Main from './components/Main/Main';
import Company from './data/Company';
import EmailAddress from './data/EmailAddress';
import Gender from './data/Gender';
import Human from './data/Human';
import MailingAddress from './data/MailingAddress';
import PhoneNumber from './data/PhoneNumber';
import Siren from './data/Siren';
import Siret from './data/Siret';
import HomeView from './views/Home/HomeView';
import MeView from './views/Me/MeView';
import CompanyView from './views/Company/CompanyView';
import CompaniesView from './views/Companies/CompaniesView';
import Employee from './data/Employee';
import WorkingTimeView from './views/WorkingTime/WorkingTimeView';

function App() {
  const [companyName] = useState<string>(String(process.env.REACT_APP_COMPANY_NAME));
  const [meAsHuman, setMeAsHuman] = useState<Human|null>(null);
  const [meAsEmployee, setMeAsEmployee] = useState<Employee|null>(null);
  const [myCompanies, setMyCompanies] = useState<Array<Company>>([]);
  const [mySelectedCompany, setMySelectedCompany] = useState<Company|null>(null);

  useEffect(() => {

    const meHuman: Human = new Human("Nicolas", "METIVIER", new Date(1993,0,6), Gender.MAN);
    setMeAsHuman(meHuman);
    const meAsNirahEmployee: Employee = Employee.builder(meHuman)
      .withEmailAddress(new EmailAddress("nicolas.metivier", "nirah-technology.fr"))
      .withHiringDate(new Date(2023, 11, 30))
      .withJobTitle("Gérant")
      .withMailingAddress(new MailingAddress("40 Route de Pelleport, 31480 Le Grès, FRANCE"))
      .withPhoneNumber(new PhoneNumber(33, 623335703))
      .build();
    const meAsAdelyaEmployee: Employee = Employee.builder(meHuman)
      .withEmailAddress(new EmailAddress("nicolas.metivier", "adelya.com"))
      .withHiringDate(new Date(2023, 1, 2))
      .withPhoneNumber(new PhoneNumber(33, 623335703))
      .withJobTitle("Architecte Logiciel")
      .withMailingAddress(new MailingAddress("40 Route de Pelleport, 31480 Le Grès, FRANCE"))
      .build();

    const nirah: Company = Company.builder()
      .withName("Nirah-Technology")
      .withCreationDate(new Date(2023, 12, 24))
      .withMailingAddress(new MailingAddress("40 Route de Pelleport, 31480 Le Grès, FRANCE"))
      .withPhoneNumber(new PhoneNumber(33, 623335703))
      .withEmailAddress(new EmailAddress("nirah.tehnology", "gmail.com"))
      .withSiret(Siret.create(11111111111111))
      .withSiren(Siren.create(999999999))
      .withEmployee(meAsNirahEmployee)
      .build();
    const adelya: Company = Company.builder()
      .withName("Adelya")
      .withCreationDate(new Date(2003, 11, 8))
      .withMailingAddress(new MailingAddress("298 Allée Du Lac, 31670 Labège, FRANCE"))
      .withPhoneNumber(new PhoneNumber(33, 623335703))
      .withEmailAddress(new EmailAddress("contact", "adelya.com"))
      .withSiret(Siret.create(45113016500033))
      .withSiren(Siren.create(451130165))
      .withEmployee(meAsAdelyaEmployee)
      .build();

      setMyCompanies(new Array(nirah, adelya));
      setMySelectedCompany(nirah);

  }, []);

  const changeSelectedCompanyHandler = (selectedCompany: Company) => {
    setMySelectedCompany(selectedCompany);
    setMeAsEmployee(selectedCompany.getEmployees()[0]);
  }

  return (
     <Routes>
        {/* Dashboard de mes activité */}
        <Route path="/" element={<Main company={companyName} />}> 
          <Route index element={<HomeView company={companyName} />} />
          {/* Résumé sur moi en tant qu'employé */}
          <Route path="/me" element={<MeView human={meAsHuman} companies={myCompanies} onSelectCompany={changeSelectedCompanyHandler} />} />
          {/* Liste de mes entrerpsies */}
          <Route path="/my-companies" element={<CompaniesView companies={myCompanies} onSelectCompany={changeSelectedCompanyHandler} />} />
          <Route path="/my-companies/:siret" element={<CompanyView company={mySelectedCompany} employee={meAsEmployee} />} />
          <Route path="/my-companies/:siret/working-time" element={<WorkingTimeView company={mySelectedCompany} employee={meAsEmployee} />} />
          {/* List */}
        </Route>
      </Routes>
  );
}

export default App;
