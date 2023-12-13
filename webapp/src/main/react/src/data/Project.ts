import Client from "./Client";
import { CriticalPathCalculator } from "./CriticalPathCalculator";
import Duration from "./Duration";
import LocalDate from "./LocalDate";
import { ProjectIdentifier } from "./ProjectIdentifier";
import ProjectMember from "./ProjectMember";
import { ProjectMemberIdentifier } from "./ProjectMemberIdentifier";
import Status from "./Status";
import Team from "./Team";
import { WorkPackage } from "./WorkPackage";

class ProjectBuilder {
  private id: ProjectIdentifier = new ProjectIdentifier("");
  private name: string;
  private description: string = "";
  private director: ProjectMember;
  private startDate: LocalDate = LocalDate.now();
  private status: Status = Status.IDLE;
  private duration: Duration = Duration.ofDays(90);
  private teams: Set<Team> = new Set();
  private workPackages: Set<WorkPackage> = new Set();
  private client: Client;

  constructor(name: string, director: ProjectMember, client: Client) {
    this.name = name;
    this.director = director;
    this.client = client;
  }

  withDescription(description: string): ProjectBuilder {
    this.description = description;
    return this;
  }

  withDirector(director: ProjectMember): ProjectBuilder {
    this.director = director;
    return this;
  }

  withStartDate(startDate: LocalDate): ProjectBuilder {
    this.startDate = startDate;
    return this;
  }

  withStatus(status: Status): ProjectBuilder {
    this.status = status;
    return this;
  }

  withDuration(duration: Duration): ProjectBuilder {
    this.duration = duration;
    return this;
  }

  withTeams(teams: Set<Team>): ProjectBuilder {
    this.teams = teams;
    return this;
  }

  withWorkPackages(workPackages: Set<WorkPackage>): ProjectBuilder {
    this.workPackages = workPackages;
    return this;
  }

  withClient(client: Client): ProjectBuilder {
    this.client = client;
    return this;
  }

  build(): Project {
    return new Project(
      this.id,
      this.name,
      this.description,
      this.director,
      this.startDate,
      this.duration,
      this.teams,
      this.status,
      this.workPackages,
      this.client
    );
  }
}

class Project {
  private readonly id: ProjectIdentifier;
  private readonly name: string;
  private description: string;
  private director: ProjectMember;
  private startDate: LocalDate;
  private status: Status;
  private duration: Duration;
  private readonly teams: Set<Team>;
  private readonly workPackages: Set<WorkPackage>;
  private readonly eventListeners: Map<Status, Function>;
  private readonly client: Client;

  constructor(
    id: ProjectIdentifier,
    name: string,
    description: string,
    director: ProjectMember,
    startDate: LocalDate,
    duration: Duration,
    teams: Set<Team>,
    status: Status,
    workPackages: Set<WorkPackage>,
    client: Client
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.director = director;
    this.startDate = startDate;
    this.duration = duration;
    this.teams = teams;
    this.status = status;
    this.workPackages = workPackages;
    this.eventListeners = new Map<Status, Function>();
    this.client = client;
  }

  getDescription(): string {
    return this.description;
  }

  getDirector(): ProjectMember {
    return this.director;
  }

  getDuration(): Duration {
    return this.duration;
  }

  getId(): ProjectIdentifier {
    return this.id;
  }

  getName(): string {
    return this.name;
  }

  getWorkPackages(): Set<WorkPackage> {
    return this.workPackages;
  }

  getStartDate(): LocalDate {
    return this.startDate;
  }

  getStatus(): Status {
    return this.status;
  }

  getTeams(): Set<Team> {
    return this.teams;
  }
  getClient(): Client {
    return this.client;
  }

  setDescription(description: string): void {
    this.description = description;
  }

  setDirector(director: ProjectMember): void {
    this.director = director;
  }

  setDuration(duration: Duration): void {
    this.duration = duration;
  }

  setStartDate(startDate: LocalDate): void {
    this.startDate = startDate;
  }

  setStatus(status: Status): void {
    this.status = status;
    if (this.eventListeners.has(status)) {
      const callback = this.eventListeners.get(status);
      if (callback) {
        callback();
      }
    }
  }

  computeCumulativeWorkingDays(): number {
    return Array.from(this.workPackages)
      .map((workPackage) => workPackage.computeCumulativeWorkingDays())
      .reduce((acc, curr) => acc + curr, 0);
  }

  getEstimatedEndDate(): LocalDate | undefined {
    const timestamp: number = Array.from(this.workPackages)
      .map((workPackage) => workPackage.getEstimatedEndDate()?.toEpochDay() || 0)
      .reduce((maxTimestamp, currentTimestamp) => Math.max(maxTimestamp, currentTimestamp), 0);

    if (timestamp === 0) {
      return undefined;
    }

    return LocalDate.ofEpochDay(timestamp);
  }

  computeMaxCriticalPathValue(): number {
    return CriticalPathCalculator.computeProject(this);
  }

  addEventListenerOnStatusChanged(status: Status, callback: Function): void {
    this.eventListeners.set(status, callback);
  }

  static builder(name: string, director: ProjectMember, client: Client): ProjectBuilder {
    return new ProjectBuilder(name, director, client);
  }

}

export default Project;