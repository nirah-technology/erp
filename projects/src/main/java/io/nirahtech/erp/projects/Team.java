package io.nirahtech.erp.projects;

import java.util.Set;

public record Team(
    String name,
    String description,
    ProjectMember manager,
    Set<ProjectMember> members
) {
    
}
