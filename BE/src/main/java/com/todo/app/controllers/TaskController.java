package com.todo.app.controllers;

import com.todo.app.models.Task;
import com.todo.app.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = this.taskService.getTasks();

        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = this.taskService.createTask(task);

        if (savedTask == null) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(savedTask, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task savedTask = taskService.updateTask(task);

        if (savedTask == null) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(savedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTask(@PathVariable("id") String id) {
        Boolean result = taskService.deleteTaskById(id);

        return result ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}
