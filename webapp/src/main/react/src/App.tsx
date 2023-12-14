import { useEffect, useState } from 'react';

import { Route, Routes } from "react-router-dom";


import { LocalDate, LocalDateTime, Month, TimeUnit } from '@nirahtech/datetime';
import './App.css';
import Main from './components/Main/Main';
import CompanyProfileView from './views/CompanyProfileView/CompanyProfileView';
import CompanyProjectsView from './views/CompanyProjectsView/CompanyProjectsView';
import EmployeeProfileView from './views/EmployeeProfileView/EmployeeProfileView';
import HomeView from './views/HomeView/HomeView';
import HumanJobsView from './views/HumanJobsView/HumanJobsView';
import HumanPlanningView from './views/HumanPlanningView/HumanPlanningView';
import HumanProfileView from './views/HumanProfileView/HumanProfileView';
import MeView from './views/MeView/MeView';
import { Client, Company, EmailAddress, Employee, Gender, Human, Imputation, MailingAddress, PhoneNumber, Project, ProjectMember, ProjectMemberIdentifier, Siren, Siret } from '@nirahtech/erp';

function App() {
  const [companyName] = useState<string>(String(process.env.REACT_APP_COMPANY_NAME));
  const [meAsHuman, setMeAsHuman] = useState<Human | null>(null);
  const [meAsEmployee, setMeAsEmployee] = useState<Employee | null>(null);
  const [myCompanies, setMyCompanies] = useState<Array<Company>>([]);
  const [mySelectedCompany, setMySelectedCompany] = useState<Company | null>(null);

  useEffect(() => {

    const meHuman: Human = Human.of("Nicolas", "METIVIER", LocalDate.of(1993, Month.JANUARY, 6), Gender.MAN);
    setMeAsHuman(meHuman);
    const meAsNirahEmployee: Employee = Employee.builder(meHuman)
      .withEmailAddress(EmailAddress.of("nicolas.metivier", "nirah-technology.fr"))
      .withHiringDate(LocalDate.of(2023, 11, 30))
      .withJobTitle("Gérant")
      .withMailingAddress(MailingAddress.of("40 Route de Pelleport, 31480 Le Grès, FRANCE"))
      .withPhoneNumber(PhoneNumber.of(33, 623335703))
      .build();
    const meAsAdelyaEmployee: Employee = Employee.builder(meHuman)
      .withEmailAddress(EmailAddress.of("nicolas.metivier", "adelya.com"))
      .withHiringDate(LocalDate.of(2023, Month.FEBRUARY, 2))
      .withPhoneNumber(PhoneNumber.of(33, 623335703))
      .withJobTitle("Architecte Logiciel")
      .withMailingAddress(MailingAddress.of("40 Route de Pelleport, 31480 Le Grès, FRANCE"))
      .build();

    const nirah: Company = Company.builder()
      .withName("Nirah-Technology")
      .withCreationDate(LocalDate.of(2023, Month.DECEMBER, 24))
      .withMailingAddress(MailingAddress.of("40 Route de Pelleport, 31480 Le Grès, FRANCE"))
      .withPhoneNumber(PhoneNumber.of(33, 623335703))
      .withEmailAddress(EmailAddress.of("nirah.tehnology", "gmail.com"))
      .withSiret(Siret.of(11111111111111))
      .withSiren(Siren.of(999999999))
      .withEmployee(meAsNirahEmployee)
      .build();
    const adelya: Company = Company.builder()
      .withName("Adelya")
      .withCreationDate(LocalDate.of(2003, Month.NOVEMBER, 8))
      .withMailingAddress(MailingAddress.of("298 Allée Du Lac, 31670 Labège, FRANCE"))
      .withPhoneNumber(PhoneNumber.of(33, 623335703))
      .withEmailAddress(EmailAddress.of("contact", "adelya.com"))
      .withSiret(Siret.of(45113016500033))
      .withSiren(Siren.of(451130165))
      .withEmployee(meAsAdelyaEmployee)
      .build();

    const meAsNirahClient: Client = new Client();
    const meAsProjectMember: ProjectMember = ProjectMember.of(new ProjectMemberIdentifier(""), meAsNirahEmployee);
    const erpProjectForNirah: Project = Project.builder("ERP", meAsProjectMember, meAsNirahEmployee).withClient(meAsNirahClient).build();
    nirah.getProjectsRegistry().register(erpProjectForNirah);

    meAsNirahEmployee.getWorkTimeSheet().impute(Imputation.of(LocalDateTime.now(), 4, TimeUnit.HOURS, erpProjectForNirah, ""));

    setMyCompanies(new Array(nirah, adelya));
    // setMySelectedCompany(nirah);

  }, []);

  const changeSelectedCompanyHandler = (selectedCompany: Company) => {
    setMySelectedCompany(selectedCompany);
    setMeAsEmployee(selectedCompany.getEmployees()[0]);
  }

  return (
    <Routes>
      {/* Dashboard de mes activité */}
      <Route path="/" element={<Main company={companyName} selectedCompany={mySelectedCompany} />}>
        <Route index element={<HomeView company={companyName} />} />
        {/* Résumé sur moi en tant qu'employé */}
        <Route path="/me" element={<MeView human={meAsHuman} companies={myCompanies} onSelectCompany={changeSelectedCompanyHandler} />} />
        <Route path="/me/profile" element={<HumanProfileView human={meAsHuman} />} />
        <Route path="/me/planning" element={<HumanPlanningView human={meAsHuman} companies={myCompanies} />} />
        <Route path="/me/jobs" element={<HumanJobsView human={meAsHuman} companies={myCompanies} onSelectCompany={changeSelectedCompanyHandler} />} />
        {/* Liste de mes entrerpsies */}
        <Route path="/work" element={<CompanyProfileView company={mySelectedCompany} employee={meAsEmployee} />} />
        <Route path="/work/profile" element={<EmployeeProfileView employee={meAsEmployee} />} />
        <Route path="/work/clients" element={<EmployeeProfileView employee={meAsEmployee} />} />
        <Route path="/work/projects" element={<CompanyProjectsView company={mySelectedCompany} />} />
        {/* List */}
      </Route>
    </Routes>
  );
}

export default App;
