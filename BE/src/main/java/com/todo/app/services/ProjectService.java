package com.todo.app.services;

import com.todo.app.models.Project;
import com.todo.app.models.Task;
import com.todo.app.repositories.ProjectRepository;
import com.todo.app.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProjectService {

    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        if (project.getTitle() == null) {
            return null;
        }

        return projectRepository.save(project);
    }

    public Project updateProject(Project project) {
        if (project.getTitle() == null && project.getId() == null && project.getId() == null) {
            return null;
        }

        Optional<Project> optionalProject = projectRepository.findById(project.getId());

        if (!optionalProject.isPresent()) {
            return null;
        }

        Project inDbProject = optionalProject.get();

        project.setTaskList(inDbProject.getTaskList());

        return projectRepository.save(project);
    }

    public Boolean deleteProjectById(String projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (!optionalProject.isPresent()) {
            return false;
        }

        Project project = optionalProject.get();

        List<Task> taskList = project.getTaskList();

        if (taskList != null) {
            taskRepository.deleteAll(taskList);
        }

        projectRepository.deleteById(projectId);

        return true;
    }
}
