package io.nirahtech.erp.projects;

import java.util.Set;

public record Project(String name, Set<Task> tasks) {
   
}