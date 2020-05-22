package com.todo.app.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Project {
    @Id
    private String id;

    private String title;
    private List<Task> taskList;

    public Project() {
    }

    public Project(String title, List<Task> taskList) {
        this.title = title;
        this.taskList = taskList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
