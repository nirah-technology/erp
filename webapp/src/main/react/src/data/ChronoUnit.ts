import LocalDateTime from "./LocalDateTime";

class ChronoUnit {
    static readonly NANOS = new ChronoUnit("Nanos", 1);
    static readonly MICROS = new ChronoUnit("Micros", 1000 * ChronoUnit.NANOS._multiplier);
    static readonly MILLIS = new ChronoUnit("Millis", 1000 * ChronoUnit.MICROS._multiplier);
    static readonly SECONDS = new ChronoUnit("Seconds", 1000 * ChronoUnit.MILLIS._multiplier);
    static readonly MINUTES = new ChronoUnit("Minutes", 60 * ChronoUnit.SECONDS._multiplier);
    static readonly HOURS = new ChronoUnit("Hours", 60 * ChronoUnit.MINUTES._multiplier);
    static readonly HALF_DAYS = new ChronoUnit("HalfDays", 12 * ChronoUnit.HOURS._multiplier);
    static readonly DAYS = new ChronoUnit("Days", 24 * ChronoUnit.HOURS._multiplier);
    static readonly WEEKS = new ChronoUnit("Weeks", 7 * ChronoUnit.DAYS._multiplier);
    static readonly MONTHS = new ChronoUnit("Months", 30 * ChronoUnit.DAYS._multiplier);
    static readonly YEARS = new ChronoUnit("Years", 365 * ChronoUnit.DAYS._multiplier);
    static readonly DECADES = new ChronoUnit("Decades", 10 * ChronoUnit.YEARS._multiplier);
    static readonly CENTURIES = new ChronoUnit("Centuries", 100 * ChronoUnit.YEARS._multiplier);
    static readonly MILLENNIA = new ChronoUnit("Millennia", 1000 * ChronoUnit.YEARS._multiplier);
    static readonly ERAS = new ChronoUnit("Eras", 1);
  
    private readonly _name: string;
    private readonly _multiplier: number;
  
    constructor(name: string, multiplier: number) {
      this._name = name;
      this._multiplier = multiplier;
    }
  
    toString(): string {
      return this._name;
    }
  
    between(startInclusive: LocalDateTime, endExclusive: LocalDateTime): number {
        const startInMs = startInclusive.toMillis();
        const endInMs = endExclusive.toMillis();
        return Math.floor((endInMs - startInMs) / this._multiplier);
    }
  }
  
  export default ChronoUnit;
  