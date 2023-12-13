import EmailAddress from "./EmailAddress";
import Employee from "./Employee";
import LocalDate from "./LocalDate";
import MailingAddress from "./MailingAddress";
import PhoneNumber from "./PhoneNumber";
import ProjectsRegistry from "./ProjectsRegistry";
import Siren from "./Siren";
import Siret from "./Siret";
import WorkActivity from "./WorkActivity";

class Builder {
  private name: string = '';
    private creationDate: LocalDate = LocalDate.now();
    private mailingAddress!: MailingAddress;
    private phoneNumber!: PhoneNumber;
    private emailAddress!: EmailAddress;
    private siret!: Siret;
    private siren!: Siren;
    private employees: Set<Employee> = new Set<Employee>();
    private activities: Set<WorkActivity> = new Set<WorkActivity>();
    private clients: Set<Client> = new Set<Client>();

    constructor() {}

    withName(name: string): Builder {
        this.name = name;
        return this;
    }

    withCreationDate(creationDate: LocalDate): Builder {
        this.creationDate = creationDate;
        return this;
    }

    withMailingAddress(mailingAddress: MailingAddress): Builder {
        this.mailingAddress = mailingAddress;
        return this;
    }

    withPhoneNumber(phoneNumber: PhoneNumber): Builder {
        this.phoneNumber = phoneNumber;
        return this;
    }

    withEmailAddress(emailAddress: EmailAddress): Builder {
        this.emailAddress = emailAddress;
        return this;
    }

    withSiret(siret: Siret): Builder {
        this.siret = siret;
        return this;
    }

    withSiren(siren: Siren): Builder {
        this.siren = siren;
        return this;
    }

    withEmployee(employee: Employee): Builder {
        this.employees.add(employee);
        return this;
    }

    withActivity(activity: WorkActivity): Builder {
        this.activities.add(activity);
        return this;
    }

    withClient(client: Client): Builder {
        this.clients.add(client);
        return this;
    }

    build(): Company {
        return new Company(
            this.name,
            this.creationDate,
            this.mailingAddress,
            this.phoneNumber,
            this.emailAddress,
            this.siret,
            this.siren,
            this.employees,
            this.activities,
            this.clients
        );
    }

}

class Company {
    private readonly name: string;
    private readonly creationDate: LocalDate;
    private readonly mailingAddress: MailingAddress;
    private readonly phoneNumber: PhoneNumber;
    private readonly emailAddress: EmailAddress;
    private readonly siret: Siret;
    private readonly siren: Siren;
    private readonly employees: Set<Employee>;
    private readonly activities: Set<WorkActivity>;
    private readonly clients: Set<Client>;
    private readonly projectsRegistry: ProjectsRegistry = new ProjectsRegistry();
  
    constructor(
      name: string,
      creationDate: LocalDate,
      mailingAddress: MailingAddress,
      phoneNumber: PhoneNumber,
      emailAddress: EmailAddress,
      siret: Siret,
      siren: Siren,
      employees: Set<Employee>,
      activities: Set<WorkActivity>,
      clients: Set<Client>
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
      this.clients = clients;
    }
  
    getName(): string {
      return this.name;
    }
  
    getCreationDate(): LocalDate {
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
  
    getEmployees(): Array<Employee> {
      return Array.from(this.employees);
    }
  
    getActivities(): Array<WorkActivity> {
      return Array.from(this.activities);
    }
    getProjectsRegistry(): ProjectsRegistry {
      return this.projectsRegistry; 
    }
    getClients(): Array<Client> {
      return Array.from(this.clients);
    }

    static builder(): Builder {
      return new Builder();
    }

  }

  export default Company;