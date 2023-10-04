package io.nirahtech.erp.projects;

import java.util.Set;

public record Team(
    TeamIdentifier id,
    String name,
    String description,
    ProjectMember manager,
    Set<ProjectMember> members
) {
    
}
