import Client from "./Client";
import { CriticalPathCalculator } from "./CriticalPathCalculator";
import { ProjectIdentifier } from "./ProjectIdentifier";
import ProjectMember from "./ProjectMember";
import Status from "./Status";
import Team from "./Team";
import { WorkPackage } from "./WorkPackage";

class Project {
    private readonly id: ProjectIdentifier;
    private readonly name: string;
    private description: string;
    private director: ProjectMember;
    private startDate: Date;
    private status: Status;
    private duration: Duration;
    private readonly teams: Set<Team>;
    private readonly workPackages: Set<WorkPackage>;
    private readonly eventListeners: Map<Status, Function>;
  
    constructor(
      id: ProjectIdentifier,
      name: string,
      description: string,
      director: ProjectMember,
      startDate: Date,
      duration: Duration,
      teams: Set<Team>,
      status: Status,
      workPackages: Set<WorkPackage>
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
  
    getStartDate(): Date {
      return this.startDate;
    }
  
    getStatus(): Status {
      return this.status;
    }
  
    getTeams(): Set<Team> {
      return this.teams;
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
  
    setStartDate(startDate: Date): void {
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
  
    getEstimatedEndDate(): Date | undefined {
      const timestamp = Array.from(this.workPackages)
        .map((workPackage) => workPackage.getEstimatedEndDate()?.toEpochDay() || 0)
        .reduce((maxTimestamp, currentTimestamp) => Math.max(maxTimestamp, currentTimestamp), 0);
  
      if (timestamp === 0) {
        return undefined;
      }
  
      return Date.ofEpochDay(timestamp);
    }
  
    computeMaxCriticalPathValue(): number {
      return CriticalPathCalculator.compute(this);
    }
  
    addEventListenerOnStatusChanged(status: Status, callback: Function): void {
      this.eventListeners.set(status, callback);
    }
  }
  
export default Project;