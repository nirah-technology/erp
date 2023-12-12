class MailingAddress {
    private readonly address: string;
  
    constructor(address: string) {
      this.address = address;
    }
  
    getAddress(): string {
      return this.address;
    }
  }
  
export default MailingAddress;