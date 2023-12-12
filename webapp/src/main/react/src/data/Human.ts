import Gender from "./Gender";

class Human {
    private readonly firstName: string;
    private readonly lastName: string;
    private readonly birthDate: Date;
    private readonly gender: Gender;
  
    constructor(firstName: string, lastName: string, birthDate: Date, gender: Gender) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.birthDate = birthDate;
      this.gender = gender;
    }
  
    getFirstName(): string {
      return this.firstName;
    }
  
    getLastName(): string {
      return this.lastName;
    }
  
    getBirthDate(): Date {
      return this.birthDate;
    }
  
    getGender(): Gender {
      return this.gender;
    }
  }
export default Human;  