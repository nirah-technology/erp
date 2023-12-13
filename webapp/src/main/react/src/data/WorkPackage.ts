import { Milestone } from "./Milestone";
import Status from "./Status";
import { Task } from "./Task";

class WorkPackage {
  private readonly id: WorkPackageIdentifier;
  private readonly name: string;
  private description: string | null = null;
  private referent: ProjectMember | null = null;
  private startDate: Date = new Date();
  private status: Status = Status.IDLE;
  private duration: Duration;
  private readonly documentations: Set<File> = new Set<File>();
  private readonly labels: Set<string> = new Set<string>();
  private readonly tasks: Set<Task> = new Set<Task>();
  private readonly milestones: Set<Milestone> = new Set<Milestone>();
  private readonly eventListeners: Map<Status, Function> = new Map<Status, Function>();

  constructor(
    id: WorkPackageIdentifier,
    name: string,
    description: string | null,
    referent: ProjectMember | null,
    startDate: Date,
    duration: Duration,
    status: Status,
    documentations: Set<File>,
    labels: Set<string>,
    tasks: Set<Task>,
    milestones: Set<Milestone>
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.referent = referent;
    this.startDate = startDate;
    this.duration = duration;
    this.status = status;
    this.documentations = new Set<File>(documentations);
    this.labels = new Set<string>(labels);
    this.tasks = new Set<Task>(tasks);
    this.milestones = new Set<Milestone>(milestones);
    this.eventListeners = new Map<Status, Function>();
  }

  getDescription(): string | null {
    return this.description;
  }

  setDescription(description: string | null): void {
    this.description = description;
  }

  setReferent(referent: ProjectMember | null): void {
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

  addTask(task: Task): void {
    this.tasks.add(task);
  }

  addTasks(...tasks: Task[]): void {
    this.tasks.add(...tasks);
  }

  addLabel(label: string): void {
    this.labels.add(label);
  }

  addLabels(...labels: string[]): void {
    this.labels.add(...labels);
  }

  addDocumentation(documentation: File): void {
    this.documentations.add(documentation);
  }

  addDocumentations(...documentations: File[]): void {
    this.documentations.add(...documentations);
  }

  addMilestone(milestone: Milestone): void {
    this.milestones.add(milestone);
  }

  addMilestones(...milestones: Milestone[]): void {
    this.milestones.add(...milestones);
  }

  getDocumentations(): Set<File> {
    return this.documentations;
  }

  getDuration(): Duration {
    return this.duration;
  }

  getId(): WorkPackageIdentifier {
    return this.id;
  }

  getLabels(): Set<string> {
    return this.labels;
  }

  getMilestones(): Set<Milestone> {
    return this.milestones;
  }

  getName(): string {
    return this.name;
  }

  getReferent(): ProjectMember | null {
    return this.referent;
  }

  getStartDate(): Date {
    return this.startDate;
  }

  getStatus(): Status {
    return this.status;
  }

  getTasks(): Set<Task> {
    return this.tasks;
  }

  getDeadLine(): Date {
    return this.startDate.plus(this.duration);
  }

  setDuration(duration: Duration): void {
    this.duration = duration;
  }

  getEstimatedEndDate(): Date | undefined {
    let mostFutureTask: Task | undefined = undefined;

    for (const task of this.tasks) {
      if (!mostFutureTask || task.getDeadLine().isAfter(mostFutureTask.getDeadLine())) {
        mostFutureTask = task;
      }
    }

    return mostFutureTask ? mostFutureTask.getDeadLine() : undefined;
  }

  computeCumulativeWorkingDays(): number {
    return Array.from(this.tasks).reduce((totalDays, task) => totalDays + task.getDuration().toDays(), 0);
  }

  computeMaxCriticalPathValue(): number {
    // Calculate critical path value for this work package
    return 0; // Placeholder, replace with actual calculation
  }

  addEventListenerOnStatusChanged(status: Status, callback: Function): void {
    this.eventListeners.set(status, callback);
  }
}

export { WorkPackage };
