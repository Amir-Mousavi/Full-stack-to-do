package com.todo.app.controllers;

import com.todo.app.models.Project;
import com.todo.app.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> allProjects = this.projectService.getAllProjects();

        return new ResponseEntity(allProjects, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project savedProject = projectService.createProject(project);

        return savedProject == null
                ? new ResponseEntity(HttpStatus.BAD_REQUEST)
                : new ResponseEntity(savedProject, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        Project savedProject = projectService.updateProject(project);

        return savedProject == null
                ? new ResponseEntity(HttpStatus.BAD_REQUEST)
                : new ResponseEntity(savedProject, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProjectById(@PathVariable("id") String id) {
        Boolean result = projectService.deleteProjectById(id);

        return result ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}
