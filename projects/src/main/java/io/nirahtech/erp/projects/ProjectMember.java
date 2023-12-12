package io.nirahtech.erp.projects;

public class ProjectMember {
    private final ProjectMemberIdentifier id;

    public ProjectMember(ProjectMemberIdentifier id) {
        this.id = id;
    }

    public ProjectMemberIdentifier getId() {
        return id;
    }
}
