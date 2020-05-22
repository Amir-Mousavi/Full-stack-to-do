package com.todo.app.services;

import com.todo.app.models.Project;
import com.todo.app.repositories.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
