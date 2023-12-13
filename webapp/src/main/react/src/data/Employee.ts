import WorkTimeWeekSheet from '../components/WorkTimeWeekSheet/WorkTimeWeekSheet';
import EmailAddress from './EmailAddress';
import EmploymentContract from './EmploymentContract';
import Gender from './Gender';
import Human  from './Human';
import MailingAddress from './MailingAddress';
import PhoneNumber from './PhoneNumber';
import WorkTimeSheet from './WorkTimeSheet';

class Builder {
  private human!: Human;
  private phoneNumber!: PhoneNumber;
  private emailAddress!: EmailAddress;
  private mailingAddress!: MailingAddress;
  private jobTitle!: string;
  private hiringDate!: Date;
  private employmentContract!: EmploymentContract;
  private worktimeSheet: WorkTimeSheet = new WorkTimeSheet();

  constructor(human: Human) {
    this.human = human;
  }
  
  withPhoneNumber(phoneNumber: PhoneNumber): Builder {
    this.phoneNumber = phoneNumber;
    return this;
  }
  withEmailAddress(emailAddress: EmailAddress): Builder {
    this.emailAddress = emailAddress;
    return this;
  }
  withMailingAddress(mailingAddress: MailingAddress): Builder {
    this.mailingAddress = mailingAddress;
    return this;
  }
  withJobTitle(jobTitle: string): Builder {
    this.jobTitle = jobTitle;
    return this;
  }
  withHiringDate(hiringDate: Date): Builder {
    this.hiringDate = hiringDate;
    return this;
  }
  withEmploymentContract(employmentContract: EmploymentContract): Builder {
    this.employmentContract = employmentContract;
    return this;
  }
  withWorkTimeSheet(worktimeSheet: WorkTimeSheet): Builder {
    this.worktimeSheet = worktimeSheet;
    return this;
  }
  build(): Employee {
    return new Employee(
      this.human.getFirstName(),
      this.human.getLastName(),
      this.human.getBirthDate(),
      this.human.getGender(),
      this.phoneNumber,
      this.emailAddress,
      this.mailingAddress,
      this.jobTitle,
      this.hiringDate,
      this.employmentContract,
      this.worktimeSheet
    );
  }

}

class Employee extends Human {
  private readonly phoneNumber: PhoneNumber;
  private readonly emailAddress: EmailAddress;
  private readonly mailingAddress: MailingAddress;
  private jobTitle: string;
  private readonly hiringDate: Date;
  private readonly employmentContract: EmploymentContract;
  private readonly worktimeSheet: WorkTimeSheet;

  constructor(
    firstName: string,
    lastName: string,
    birthDate: Date,
    gender: Gender,
    phoneNumber: PhoneNumber,
    emailAddress: EmailAddress,
    mailingAddress: MailingAddress,
    jobTitle: string,
    hiringDate: Date,
    employmentContract: EmploymentContract,
    worktimeSheet: WorkTimeSheet
  ) {
    super(firstName, lastName, birthDate, gender);
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.mailingAddress = mailingAddress;
    this.jobTitle = jobTitle;
    this.hiringDate = hiringDate;
    this.employmentContract = employmentContract;
    this.worktimeSheet = worktimeSheet;
  }

  getEmailAddress(): EmailAddress {
    return this.emailAddress;
  }

  getMailingAddress(): MailingAddress {
    return this.mailingAddress;
  }

  getPhoneNumber(): PhoneNumber {
    return this.phoneNumber;
  }

  getJobTitle(): string {
    return this.jobTitle;
  }

  getHiringDate(): Date {
    return this.hiringDate;
  }
  
  getEmploymentContract(): EmploymentContract {
    return this.employmentContract;
  }
  getWorkTimeSheet(): WorkTimeSheet {
    return this.worktimeSheet;
  }

  public static builder(human: Human): Builder {
    return new Builder(human);
  }
}
export default Employee;