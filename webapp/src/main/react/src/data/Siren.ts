class Siren {
    private readonly code: number;
    private static readonly CODE_LENGTH: number = 9;
  
    private constructor(code: number) {
      this.code = code;
    }
  
    static create(code: number): Siren {
      if (String(code).length !== Siren.CODE_LENGTH) {
        throw new Error(`SIREN must be a number with ${14} characters.`);
      }
      return new Siren(code);
    }
  
    getCode(): number {
      return this.code;
    }
  }
export default Siren;  