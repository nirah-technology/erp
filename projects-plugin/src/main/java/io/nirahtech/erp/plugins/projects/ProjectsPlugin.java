package io.nirahtech.erp.plugins.projects;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.swing.JPanel;

import io.nirahtech.erp.plugins.Plugin;
import io.nirahtech.erp.plugins.Version;
import io.nirahtech.erp.plugins.projects.interfaces.gui.MainViewPanel;
import io.nirahtech.erp.projects.Project;
import io.nirahtech.erp.projects.ProjectBuilder;
import io.nirahtech.erp.projects.ProjectMember;
import io.nirahtech.erp.projects.ProjectMemberIdentifier;
import io.nirahtech.erp.projects.Status;
import io.nirahtech.erp.projects.Team;
import io.nirahtech.erp.projects.TeamIdentifier;
import io.nirahtech.erp.projects.WorkPackageBuilder;

/**
 * ProjectsPlugin
 */
public class ProjectsPlugin implements Plugin {

    private Set<Project> cachedProjects = new HashSet<>();

    Team backendTeam = new Team(new TeamIdentifier(UUID.randomUUID()), "Backend", null, new ProjectMember(new ProjectMemberIdentifier(UUID.randomUUID())), Set.of());

    public ProjectsPlugin() {
        Project project = new ProjectBuilder()
            .name("FormerValues")
            .description("MS for FM events")
            .director(new ProjectMember(new ProjectMemberIdentifier(UUID.randomUUID())))
            .startDate(LocalDate.of(2023, 02, 02))
            .duration(Duration.ofDays(187))
            .status(Status.WORK_IN_PROGRESS)
            .teams(backendTeam)
            .workPackages(new WorkPackageBuilder()
                    .name("PI32")
                    .duration(Duration.ofDays(15))
                    .referent(new ProjectMember(new ProjectMemberIdentifier(UUID.randomUUID())))
                    .startDate(LocalDate.of(2023, 10, 1))
                    .status(Status.WORK_IN_PROGRESS)
                    .build())
            .build();
        this.cachedProjects.add(project);
        project = new ProjectBuilder()
            .name("Service Directory")
            .description("Service directory for MS")
            .director(new ProjectMember(new ProjectMemberIdentifier(UUID.randomUUID())))
            .startDate(LocalDate.of(2023, 07, 24))
            .duration(Duration.ofDays(15))
            .status(Status.IDLE)
            .teams(backendTeam)
            .workPackages(new WorkPackageBuilder()
                    .name("PI40")
                    .duration(Duration.ofDays(15))
                    .referent(new ProjectMember(new ProjectMemberIdentifier(UUID.randomUUID())))
                    .startDate(LocalDate.of(2023, 07, 24))
                    .status(Status.IDLE)
                    .build())
            .build();
        this.cachedProjects.add(project);
    }


    @Override
    public void execute() {
        // Do nothing
    }

    @Override
    public String getMenuTitle() {
        return "Projects";
    }

    @Override
    public String getName() {
        return "Projects";
    }

    @Override
    public Version getVersion() {
        final Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("projects-plugin.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Version version = null;
        if (properties.containsKey("version")) {
            version = Version.parse(properties.getProperty("version"));
        }

        return version;
    }

    @Override
    public JPanel getView() {
        return new MainViewPanel(this.cachedProjects);
    }

    @Override
    public void setup() {
        // Do nothing
    }

    @Override
    public void shutdown() {
        // Do nothing
    }

    
}