import { File } from "./File";
import { ProjectMember } from "./ProjectMember";
import { Status } from "./Status";
import { TaskIdentifier } from "./TaskIdentifier";

class Task {
  private readonly id: TaskIdentifier;
  private readonly name: string;
  private readonly parent: Task | null;
  private details: string;
  private status: Status;
  private startDate: Date;
  private duration: Duration;
  private prority: Priority;
  private referent: ProjectMember;
  private maxWorkers: number;
  private readonly subTasks: Set<Task>;
  private readonly requiredTasks: Set<Task>;
  private readonly documentations: Set<File>;
  private readonly labels: Set<string>;
  private readonly workers: Set<ProjectMember>;
  private readonly eventListeners: Map<Status, Runnable>;

  constructor(
    id: TaskIdentifier,
    name: string,
    details: string,
    status: Status,
    parent: Task | null,
    subTasks: Set<Task>,
    requiredTasks: Set<Task>,
    startDate: Date,
    duration: Duration,
    prority: Priority,
    labels: Set<string>,
    documentations: Set<File>,
    referent: ProjectMember,
    workers: Set<ProjectMember>,
    maxWorkers: number
  ) {
    this.id = id;
    this.name = name;
    this.details = details;
    this.status = status;
    this.parent = parent;
    this.subTasks = new Set<Task>(subTasks);
    this.requiredTasks = new Set<Task>(requiredTasks);
    this.startDate = startDate;
    this.duration = duration;
    this.prority = prority;
    this.labels = new Set<string>(labels);
    this.documentations = new Set<File>(documentations);
    this.referent = referent;
    this.workers = new Set<ProjectMember>(workers);
    this.maxWorkers = maxWorkers;
    this.eventListeners = new Map<Status, Runnable>();
  }

  getDetails(): string {
    return this.details;
  }

  getDocumentations(): Set<File> {
    return this.documentations;
  }

  getDuration(): Duration {
    return this.duration;
  }

  getId(): TaskIdentifier {
    return this.id;
  }

  getLabels(): Set<string> {
    return this.labels;
  }

  getMaxWorkers(): number {
    return this.maxWorkers;
  }

  getName(): string {
    return this.name;
  }

  getParent(): Task | null {
    return this.parent;
  }

  getPrority(): Priority {
    return this.prority;
  }

  getReferent(): ProjectMember {
    return this.referent;
  }

  getRequiredTasks(): Set<Task> {
    return this.requiredTasks;
  }

  getStartDate(): Date {
    return this.startDate;
  }

  getStatus(): Status {
    return this.status;
  }

  getSubTasks(): Set<Task> {
    return this.subTasks;
  }

  getWorkers(): Set<ProjectMember> {
    return this.workers;
  }

  setDetails(details: string): void {
    this.details = details;
  }

  setDuration(duration: Duration): void {
    this.duration = duration;
  }

  setMaxWorkers(maxWorkers: number): void {
    this.maxWorkers = maxWorkers;
  }

  setPrority(prority: Priority): void {
    this.prority = prority;
  }

  setReferent(referent: ProjectMember): void {
    this.referent = referent;
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

  getDeadLine(): Date {
    const endDate = new Date(this.startDate);
    endDate.setDate(endDate.getDate() + this.duration);
    return endDate;
  }

  addEventListenerOnStatusChanged(status: Status, callback: Runnable): void {
    this.eventListeners.set(status, callback);
  }
}

export { Task };
