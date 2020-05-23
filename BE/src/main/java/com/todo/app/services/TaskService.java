package com.todo.app.services;

import com.todo.app.models.Project;
import com.todo.app.models.Task;
import com.todo.app.repositories.ProjectRepository;
import com.todo.app.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskService {

    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Task findTaskById(String taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        return optionalTask.isPresent() ? optionalTask.get() : null;
    }

    public Project findProjectById(String projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        return optionalProject.isPresent() ? optionalProject.get() : null;
    }

    public List<Task> getTasks() {
        return this.taskRepository.findAll();
    }

    public Task createTask(Task task) {
        if (task.getProjectId() == null) {
            return null;
        }

        Optional<Project> optionalProject = projectRepository.findById(task.getProjectId());

        if (!optionalProject.isPresent()) {
            return null;
        }

        Project project = optionalProject.get();
        List<Task> taskList = project.getTaskList();

        if (taskList == null) {
            taskList = new ArrayList();
        }

        Task savedTask = taskRepository.save(task);
        taskList.add(savedTask);
        project.setTaskList(taskList);
        projectRepository.save(project);


        return savedTask;
    }

    public Task updateTask(Task task) {
        Task inDbTask = findTaskById(task.getId());

        if (inDbTask == null) {
            return null;
        }

        Project project = findProjectById(task.getProjectId());

        if (project == null) {
            return null;
        }

        Task savedTask = taskRepository.save(task);

        List<Task> taskList = project.getTaskList();

        List<Task> newTaskList = taskList.stream().map(t -> {
            if (t.getId().equals(task.getId())) {
                return savedTask;
            }
            return t;
        }).collect(Collectors.toList());

        project.setTaskList(newTaskList);
        projectRepository.save(project);

        return savedTask;
    }

    public Boolean deleteTaskById(String taskId) {
        Task taskById = this.findTaskById(taskId);
        if (taskById == null) {
            return false;
        }

        Project project = this.findProjectById(taskById.getProjectId());

        if (project == null || project.getTaskList() == null) {
            return false;
        }

        List<Task> taskList = project.getTaskList();
        List<Task> newTaskList = taskList
                .stream()
                .filter(t -> !t.getId().equals(taskId))
                .collect(Collectors.toList());
        project.setTaskList(newTaskList);
        projectRepository.save(project);

        taskRepository.deleteById(taskId);

        return true;
    }
}
