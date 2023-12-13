import ProjectMember from "./ProjectMember";
import TeamIdentifier from "./TeamIdentifier";

class Team {
  private readonly id: TeamIdentifier;
  private readonly name: string;
  private readonly description: string;
  private readonly manager: ProjectMember;
  private readonly members: Set<ProjectMember>;

  constructor(id: TeamIdentifier, name: string, description: string, manager: ProjectMember, members: Set<ProjectMember>) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.manager = manager;
    this.members = new Set<ProjectMember>(members);
  }

  getId(): TeamIdentifier {
    return this.id;
  }

  getName(): string {
    return this.name;
  }

  getDescription(): string {
    return this.description;
  }

  getManager(): ProjectMember {
    return this.manager;
  }

  getMembers(): Set<ProjectMember> {
    return this.members;
  }
}

export default Team;
