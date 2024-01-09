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
import SocialSecurityNumber from '@nirahtech/erp/dist/src/core/SocialSecurityNumber';

function App() {
  const [companyName] = useState<string>(String(process.env.REACT_APP_COMPANY_NAME));
  const [meAsHuman, setMeAsHuman] = useState<Human | null>(null);
  const [meAsEmployee, setMeAsEmployee] = useState<Employee | null>(null);
  const [myCompanies, setMyCompanies] = useState<Array<Company>>([]);
  const [mySelectedCompany, setMySelectedCompany] = useState<Company | null>(null);

  useEffect(() => {

    const meHuman: Human = Human.of(SocialSecurityNumber.of(1_93_01_32_013_042, 30), "Nicolas", "METIVIER", LocalDate.of(1993, Month.JANUARY, 6), Gender.MAN, PhoneNumber.of(33, 623335703), EmailAddress.of("nicolas.a.metivier", "gmail.com"), MailingAddress.of("40 Route de Pelleport, 31480 Le Grès, FRANCE"));
    setMeAsHuman(meHuman);
    const meAsNirahEmployee: Employee = Employee.builder(meHuman)
      .withEmailAddress(EmailAddress.of("nicolas.metivier", "nirah-technology.fr"))
      .withHiringDate(LocalDate.of(2023, 11, 30))
      .withJobTitle("Gérant")
      .build();
    const meAsAdelyaEmployee: Employee = Employee.builder(meHuman)
      .withEmailAddress(EmailAddress.of("nicolas.metivier", "adelya.com"))
      .withHiringDate(LocalDate.of(2023, Month.FEBRUARY, 2))
      .withJobTitle("Architecte Logiciel")
      .build();
      const meAsAstekEmployee: Employee = Employee.builder(meHuman)
        .withEmailAddress(EmailAddress.of("nicolas.metivier", "astek.com"))
        .withHiringDate(LocalDate.of(2024, Month.FEBRUARY, 26))
        .withJobTitle("Ingeneer")
        .build();

    const nirah: Company = Company.builder()
      .withName("Nirah-Technology")
      .withCreationDate(LocalDate.of(2024, Month.JANUARY, 8))
      .withMailingAddress(MailingAddress.of("40 Route de Pelleport, 31480 Le Grès, FRANCE"))
      .withPhoneNumber(PhoneNumber.of(33, 623335703))
      .withEmailAddress(EmailAddress.of("nirah.technology", "gmail.com"))
      .withSiret(Siret.of(982_808_065_00014))
      .withSiren(Siren.of(982_808_065))
      .withEmployee(meAsNirahEmployee)
      .build();
    const adelya: Company = Company.builder()
      .withName("Adelya")
      .withCreationDate(LocalDate.of(2003, Month.NOVEMBER, 8))
      .withMailingAddress(MailingAddress.of("298 Allée Du Lac, 31670 Labège, FRANCE"))
      .withPhoneNumber(PhoneNumber.of(33, 623335703))
      .withEmailAddress(EmailAddress.of("contact", "adelya.com"))
      .withSiret(Siret.of(45113016500033))
      .withSiren(Siren.of(451_130_165))
      .withEmployee(meAsAdelyaEmployee)
      .build();

    const astek: Company = Company.builder()
    .withName("ASTEK")
    .withCreationDate(LocalDate.of(1988, Month.JANUARY, 1))
    .withMailingAddress(MailingAddress.of("1 Rue du Ramassier, 31700 Colomiers, FRANCE"))
    .withPhoneNumber(PhoneNumber.of(33, 623335703))
    .withEmailAddress(EmailAddress.of("contact", "astek.com"))
    .withSiret(Siret.of(34798980800568))
    .withSiren(Siren.of(347989808))
    .withEmployee(meAsAstekEmployee)
    .build();

    const meAsNirahClient: Client = new Client();
    const meAsProjectMember: ProjectMember = ProjectMember.of(new ProjectMemberIdentifier(""), meAsNirahEmployee);
    const erpProjectForNirah: Project = Project.builder("ERP", meAsProjectMember, meAsNirahEmployee).withClient(meAsNirahClient).build();
    nirah.getProjectsRegistry().register(erpProjectForNirah);

    meAsNirahEmployee.getWorkTimeSheet().impute(Imputation.of(LocalDateTime.now(), 4, TimeUnit.HOURS, erpProjectForNirah, ""));

    setMyCompanies(new Array(nirah, adelya, astek));
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
