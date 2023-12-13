import Duration from "./Duration";
import LocalDate from "./LocalDate";
import { MilestoneIdentifier } from "./MilestoneIdentifier";
import ProjectMember from "./ProjectMember";
import Status from "./Status";

class Milestone {
  private readonly id: MilestoneIdentifier;
  private readonly name: string;
  private description: string | null = null;
  private referent: ProjectMember | null = null;
  private startDate: LocalDate = LocalDate.now();
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
    startDate: LocalDate,
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

  addLabel(label: string): void {
    this.labels.add(label);
  }

  addLabels(labels: string[]): void {
    labels.forEach((label) => {
      this.labels.add(label);
    });
  }

  addDocumentation(documentation: File): void {
    this.documentations.add(documentation);
  }

  addDocumentations(documentations: File[]): void {
    documentations.forEach((documentation) => {
      this.documentations.add(documentation);
    });
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

  getStartDate(): LocalDate {
    return this.startDate;
  }

  getStatus(): Status {
    return this.status;
  }

  getDeadline(): LocalDate {
    return this.startDate.plus(this.duration);
  }

  setDuration(duration: Duration): void {
    this.duration = duration;
  }

  getEstimatedEndDate(): LocalDate | undefined {
    return this.startDate.plus(this.duration);
  }

  addEventListenerOnStatusChanged(status: Status, callback: Function): void {
    this.eventListeners.set(status, callback);
  }
}

export { Milestone };
