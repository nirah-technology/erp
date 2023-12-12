import EmailAddress from "./EmailAddress";
import Employee from "./Employee";
import MailingAddress from "./MailingAddress";
import PhoneNumber from "./PhoneNumber";
import Siren from "./Siren";
import Siret from "./Siret";
import WorkActivity from "./WorkActivity";

class Company {
    private readonly name: string;
    private readonly creationDate: Date;
    private readonly mailingAddress: MailingAddress;
    private readonly phoneNumber: PhoneNumber;
    private readonly emailAddress: EmailAddress;
    private readonly siret: Siret;
    private readonly siren: Siren;
    private readonly employees: Set<Employee>;
    private readonly activities: Set<WorkActivity>;
  
    constructor(
      name: string,
      creationDate: Date,
      mailingAddress: MailingAddress,
      phoneNumber: PhoneNumber,
      emailAddress: EmailAddress,
      siret: Siret,
      siren: Siren,
      employees: Set<Employee>,
      activities: Set<WorkActivity>
    ) {
      this.name = name;
      this.creationDate = creationDate;
      this.mailingAddress = mailingAddress;
      this.phoneNumber = phoneNumber;
      this.emailAddress = emailAddress;
      this.siret = siret;
      this.siren = siren;
      this.employees = employees;
      this.activities = activities;
    }
  
    getName(): string {
      return this.name;
    }
  
    getCreationDate(): Date {
      return this.creationDate;
    }
  
    getMailingAddress(): MailingAddress {
      return this.mailingAddress;
    }
  
    getPhoneNumber(): PhoneNumber {
      return this.phoneNumber;
    }
  
    getEmailAddress(): EmailAddress {
      return this.emailAddress;
    }
  
    getSiret(): Siret {
      return this.siret;
    }
  
    getSiren(): Siren {
      return this.siren;
    }
  
    getEmployees(): Set<Employee> {
      return this.employees;
    }
  
    getActivities(): Set<WorkActivity> {
      return this.activities;
    }
  }

  export default Company;