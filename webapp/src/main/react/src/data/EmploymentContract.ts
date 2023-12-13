class EmploymentContract {
    private readonly contractType: string;
    private readonly workedHoursByWeek: number;
    private readonly annualSalary: number;
    private readonly monthlyPay: number;
    private readonly grossHourlyRate: number;

    constructor(contractType: string, workedHoursByWeek: number, annualSalary: number, monthlyPay: number, grossHourlyRate: number) {
        this.contractType = contractType;
        this.workedHoursByWeek = workedHoursByWeek;
        this.grossHourlyRate = grossHourlyRate;
        this.annualSalary = annualSalary;
        this.monthlyPay = monthlyPay;
    }

    
    getContractType(): string {
        return this.contractType;
    }
    getWorkedHoursByWeek(): number {
        return this.workedHoursByWeek;
    }
    getGrossHourlyRate(): number {
        return this.grossHourlyRate;
    }
    
}

export default EmploymentContract;