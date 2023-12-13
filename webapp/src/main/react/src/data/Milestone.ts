import { MilestoneIdentifier } from "./MilestoneIdentifier";
import ProjectMember from "./ProjectMember";
import Status from "./Status";

class Milestone {
  private readonly id: MilestoneIdentifier;
  private readonly name: string;
  private description: string | null = null;
  private referent: ProjectMember | null = null;
  private startDate: Date = new Date();
  private status: Status = Status.IDLE;
  private duration: Duration;
  private readonly documentations: Set<File> = new Set<File>();
  private readonly labels: Set<string> = new Set<string>();
  private readonly eventListeners: Map<Status, Function> = new Map<Status, Function>();

  constructor(
    id: MilestoneIdentifier,
    name: string,
    description: string | null,
    referent: ProjectMember | null,
    startDate: Date,
    duration: Duration,
    status: Status,
    documentations: Set<File>,
    labels: Set<string>
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

  addLabel(label: string): void {
    this.labels.add(label);
  }

  addLabels(labels: string[]): void {
    this.labels.add(labels);
  }

  addDocumentation(documentation: File): void {
    this.documentations.add(documentation);
  }

  addDocumentations(...documentations: File[]): void {
    this.documentations.add(...documentations);
  }

  getDocumentations(): Set<File> {
    return this.documentations;
  }

  getDuration(): Duration {
    return this.duration;
  }

  getId(): MilestoneIdentifier {
    return this.id;
  }

  getLabels(): Set<string> {
    return this.labels;
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

  getDeadline(): Date {
    return this.startDate.plus(this.duration);
  }

  setDuration(duration: Duration): void {
    this.duration = duration;
  }

  getEstimatedEndDate(): Date | undefined {
    const estimatedEndDates: Date[] = Array.from(this.documentations).map((documentation) =>
      Date.ofEpochDay(documentation.lastModified() / (1000 * 60 * 60 * 24))
    );

    const maxEstimatedEndDate = estimatedEndDates.reduce((maxDate, currentDate) =>
      maxDate.isAfter(currentDate) ? maxDate : currentDate
    );

    return maxEstimatedEndDate;
  }

  addEventListenerOnStatusChanged(status: Status, callback: Function): void {
    this.eventListeners.set(status, callback);
  }
}

export { Milestone };
