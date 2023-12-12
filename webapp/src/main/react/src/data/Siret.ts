class Siret {
    private readonly code: number;
    private static readonly CODE_LENGTH: number = 14;
  
    private constructor(code: number) {
      this.code = code;
    }
  
    static create(code: number): Siret {
      if (String(code).length !== Siret.CODE_LENGTH) {
        throw new Error(`SIRET must be a number with ${14} characters.`);
      }
      return new Siret(code);
    }
  
    getCode(): number {
      return this.code;
    }
  }
export default Siret;  