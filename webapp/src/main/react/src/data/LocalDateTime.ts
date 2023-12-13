import Duration from "./Duration";
import LocalDate from "./LocalDate";
import LocalTime from "./LocalTime";

class LocalDateTime {
    private year: number;
    private month: number;
    private day: number;
    private hour: number;
    private minute: number;
    private second: number;
    private millisecond: number;
  
    constructor(year: number, month: number, day: number, hour: number, minute: number, second: number, millisecond: number) {
      this.year = year;
      this.month = month;
      this.day = day;
      this.hour = hour;
      this.minute = minute;
      this.second = second;
      this.millisecond = millisecond;
    }
  
    getYear(): number {
      return this.year;
    }
  
    getMonth(): number {
      return this.month;
    }
  
    getDay(): number {
      return this.day;
    }
  
    getHour(): number {
      return this.hour;
    }
  
    getMinute(): number {
      return this.minute;
    }
  
    getSecond(): number {
      return this.second;
    }
  
    toString(): string {
      return `${this.year}-${this.month}-${this.day}T${this.hour}:${this.minute}:${this.second}`;
    }
    isEqual(otherDate: LocalDateTime): boolean {
        return (
          this.year === otherDate.getYear() &&
          this.month === otherDate.getMonth() &&
          this.day === otherDate.getDay() &&
          this.hour === otherDate.getHour() &&
          this.minute === otherDate.getMinute() &&
          this.second === otherDate.getSecond()
        );
      }
    
      isBefore(otherDate: LocalDateTime): boolean {
        return this.toString() < otherDate.toString();
      }
    
      isAfter(otherDate: LocalDateTime): boolean {
        return this.toString() > otherDate.toString();
      }

      toLocalDate(): LocalDate {
        return new LocalDate(this.year, this.month, this.day);
      }

      toLocalTime(): LocalTime {
        return new LocalTime(this.hour, this.minute, this.second, this.millisecond);
      }

      plus(duration: Duration): LocalDateTime {
        const millisecondsToAdd = duration.getMilliseconds();
        const newDate = new Date(
          this.year,
          this.month - 1,
          this.day,
          this.hour,
          this.minute,
          this.second + millisecondsToAdd / 1000
        );
        return new LocalDateTime(
          newDate.getFullYear(),
          newDate.getMonth() + 1,
          newDate.getDate(),
          newDate.getHours(),
          newDate.getMinutes(),
          newDate.getSeconds(),
          newDate.getMilliseconds()
        );
      }
    
    
      minus(duration: Duration): LocalDateTime {
        const millisecondsToSubtract = duration.getMilliseconds();
        const newDate = new Date(
          this.year,
          this.month - 1,
          this.day,
          this.hour,
          this.minute,
          this.second - millisecondsToSubtract / 1000
        );
        return new LocalDateTime(
          newDate.getFullYear(),
          newDate.getMonth() + 1,
          newDate.getDate(),
          newDate.getHours(),
          newDate.getMinutes(),
          newDate.getSeconds(),
          newDate.getMilliseconds()
        );
      }
    
      static now(): LocalDateTime {
        const currentDate = new Date();
        return new LocalDateTime(
          currentDate.getFullYear(),
          currentDate.getMonth() + 1,
          currentDate.getDate(),
          currentDate.getHours(),
          currentDate.getMinutes(),
          currentDate.getSeconds(),
          currentDate.getMilliseconds()
        );
      }
      toMillis(): number {
        const dateInMillis = this.toLocalDate().toMillis();
        const timeInMillis = this.toLocalTime().toMillis();
        return dateInMillis + timeInMillis;
      }
      static from(date: Date): LocalDateTime {
        return new LocalDateTime(
          date.getFullYear(),
          date.getMonth(),
          date.getDay(),
          date.getHours(),
          date.getMinutes(),
          date.getSeconds(),
          date.getMilliseconds()
        )
      }
  }
  export default LocalDateTime;