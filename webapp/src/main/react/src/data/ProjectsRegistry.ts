import Project from "./Project";
import ProjectMember from "./ProjectMember";

class ProjectsRegistry {
    private static INSTANCE: ProjectsRegistry = new ProjectsRegistry();
    private projects: Set<Project> = new Set<Project>();

    static getInstance(): ProjectsRegistry {
        return ProjectsRegistry.INSTANCE;
    }

    register(project: Project): void {
        this.projects.add(project);
    }

    createProject(name: string, director: ProjectMember, client: Client): Project {
        const project: Project = Project.builder(name, director, client).build();
        this.register(project);
        return project;
    }

    findByName(name: string): Project | undefined {
        return Array.from(this.projects).find(project => project.getName().toLowerCase() === name.toLowerCase());
    }

    unregister(project: Project): void {
        if (project && this.projects.has(project)) {
            this.projects.delete(project);
        }
    }

    getProjects(): ReadonlySet<Project> {
        return this.projects;
    }
}
export default ProjectsRegistry;