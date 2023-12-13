class Duration {
  private milliseconds: number;

  constructor(milliseconds: number) {
    this.milliseconds = milliseconds;
  }

  getMilliseconds(): number {
    return this.milliseconds;
  }

  getSeconds(): number {
    return this.milliseconds / 1000;
  }

  getMinutes(): number {
    return this.getSeconds() / 60;
  }

  getHours(): number {
    return this.getMinutes() / 60;
  }

  getDays(): number {
    return this.getHours() / 24;
  }

  static ofDays(days: number): Duration {
    return new Duration(days * 24 * 60 * 60 * 1000);
  }

  static ofHours(hours: number): Duration {
    return new Duration(hours * 60 * 60 * 1000);
  }

  static ofMinutes(minutes: number): Duration {
    return new Duration(minutes * 60 * 1000);
  }

  plus(other: Duration): Duration {
    return new Duration(this.milliseconds + other.getMilliseconds());
  }

  plusDays(days: number): Duration {
    return new Duration(this.milliseconds + days * 24 * 60 * 60 * 1000);
  }

  plusHours(hours: number): Duration {
    return new Duration(this.milliseconds + hours * 60 * 60 * 1000);
  }

  plusMillis(millis: number): Duration {
    return new Duration(this.milliseconds + millis);
  }

  plusMinutes(minutes: number): Duration {
    return new Duration(this.milliseconds + minutes * 60 * 1000);
  }

  plusSeconds(seconds: number): Duration {
    return new Duration(this.milliseconds + seconds * 1000);
  }

  minus(other: Duration): Duration {
    return new Duration(this.milliseconds - other.getMilliseconds());
  }

  minusDays(days: number): Duration {
    return new Duration(this.milliseconds - days * 24 * 60 * 60 * 1000);
  }

  minusHours(hours: number): Duration {
    return new Duration(this.milliseconds - hours * 60 * 60 * 1000);
  }

  minusMillis(millis: number): Duration {
    return new Duration(this.milliseconds - millis);
  }

  minusMinutes(minutes: number): Duration {
    return new Duration(this.milliseconds - minutes * 60 * 1000);
  }

  minusSeconds(seconds: number): Duration {
    return new Duration(this.milliseconds - seconds * 1000);
  }
  toDays(): number {
    return Math.floor(this.milliseconds / (24 * 60 * 60 * 1000));
  }

  toHours(): number {
    return Math.floor(this.milliseconds / (60 * 60 * 1000));
  }

  toMinutes(): number {
    return Math.floor(this.milliseconds / (60 * 1000));
  }

  toMillis(): number {
    return this.milliseconds;
  }
}
export default Duration;  