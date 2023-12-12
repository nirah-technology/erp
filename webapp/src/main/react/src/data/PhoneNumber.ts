class PhoneNumber {
    private readonly countryCode: number;
    private readonly number: number;
  
    constructor(countryCode: number, number: number) {
      this.countryCode = countryCode;
      this.number = number;
    }
  
    getCountryCode(): number {
      return this.countryCode;
    }
  
    getNumber(): number {
      return this.number;
    }

    toString(): string {
      return String("+".concat(String(this.countryCode)).concat(String(this.number)));
    }
  }

  export default PhoneNumber;