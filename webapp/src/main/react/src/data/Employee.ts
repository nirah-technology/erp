import EmailAddress from './EmailAddress';
import Gender from './Gender';
import Human  from './Human';
import MailingAddress from './MailingAddress';
import PhoneNumber from './PhoneNumber';

class Employee extends Human {
  private readonly phoneNumber: PhoneNumber;
  private readonly emailAddress: EmailAddress;
  private readonly mailingAddress: MailingAddress;
  private jobTitle: string;
  private readonly hiringDate: Date;

  constructor(
    firstName: string,
    lastName: string,
    birthDate: Date,
    gender: Gender,
    phoneNumber: PhoneNumber,
    emailAddress: EmailAddress,
    mailingAddress: MailingAddress,
    jobTitle: string,
    hiringDate: Date
  ) {
    super(firstName, lastName, birthDate, gender);
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.mailingAddress = mailingAddress;
    this.jobTitle = jobTitle;
    this.hiringDate = hiringDate;
  }

  constructor(
    human: Human,
    phoneNumber: PhoneNumber,
    emailAddress: EmailAddress,
    mailingAddress: MailingAddress,
    jobTitle: string,
    hiringDate: Date) {
      this(human.getFirstName(), human.getLastName(), human.getBirthDate(), human.getGender(), phoneNumber, emailAddress, mailingAddress, jobTitle, hiringDate);
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
}
export default Employee;