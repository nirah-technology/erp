import Project from "./Project";
import { Task } from "./Task";
import { WorkPackage } from "./WorkPackage";

class CriticalPathCalculator {
  static computeProject(project: Project): number {
    const criticalPathInDays = Math.max(
      ...Array.from(project.getWorkPackages()).map((wp) => CriticalPathCalculator.computeWorkPackage(wp))
    );
    return criticalPathInDays;
  }

  static computeWorkPackage(workPackage: WorkPackage): number {
    const criticalPathInDays = Math.max(
      ...Array.from(workPackage.getTasks()).map((task) => CriticalPathCalculator.computeTask(task))
    );
    return criticalPathInDays;
  }

  static computeTask(task: Task): number {
    let days = 0;
    if (task !== null && task !== undefined) {
      let parent: Task|null = task.getParent();
      if (!parent) {
        days = task.getDuration().toDays();
      } else {
        days = task.getDuration().toDays() + CriticalPathCalculator.computeTask(parent);
      }
    }
    return days;
  }
}

export { CriticalPathCalculator };
