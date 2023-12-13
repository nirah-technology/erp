import { ProjectMemberIdentifier } from "./ProjectMemberIdentifier";

class ProjectMember {
  private readonly id: ProjectMemberIdentifier;

  constructor(id: ProjectMemberIdentifier) {
    this.id = id;
  }

  getId(): ProjectMemberIdentifier {
    return this.id;
  }
}

export default ProjectMember;
