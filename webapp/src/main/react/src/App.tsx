import { useState } from 'react';

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

function App() {
  const [companyName] = useState<string>(String(process.env.REACT_APP_COMPANY_NAME));
  const [me] = useState<Human>(new Human("Nicolas", "METIVIER", new Date(1993,0,6), Gender.MAN));
  const [myCompanies] = useState<Array<Company>>([
    new Company("Nirah-Technology", new Date(2023, 12, 24), new MailingAddress("40 Route de Pelleport, 31480 Le Grès, FRANCE"), new PhoneNumber(33, 623335703), new EmailAddress("nirah.tehnology", "gmail.com"), Siret.create(11111111111111), Siren.create(999999999), new Set(), new Set()),
    new Company("Adelya", new Date(2003, 11, 8), new MailingAddress("298 Allée Du Lac, 31670 Labège, FRANCE"), new PhoneNumber(33, 566666666), new EmailAddress("nicolas.metivier", "adelya.com"), Siret.create(45113016500033), Siren.create(451130165), new Set(), new Set()),
  ]);
  const [mySelectedCompany, setMySelectedCompany] = useState<Company>(myCompanies[0]);

  const changeSelectedCompanyHandler = (selectedCompany: Company) => {
    setMySelectedCompany(selectedCompany);
  }

  return (
     <Routes>
        {/* Dashboard de mes activité */}
        <Route path="/" element={<Main company={companyName} />}> 
          <Route index element={<HomeView company={companyName} />} />
          {/* Résumé sur moi en tant qu'employé */}
          <Route path="/me" element={<MeView human={me} companies={myCompanies} onSelectCompany={changeSelectedCompanyHandler} />} />
          {/* Liste de mes entrerpsies */}
          <Route path="/my-companies" element={<CompaniesView companies={myCompanies} onSelectCompany={changeSelectedCompanyHandler} />} />
          <Route path="/my-companies/:siret" element={<CompanyView company={mySelectedCompany} />} />
          {/* List */}
        </Route>
      </Routes>
  );
}

export default App;
