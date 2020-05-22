package com.todo.app.models;

import org.springframework.data.annotation.Id;

public class Task {
    @Id
    private String id;
    private String title;
    private String projectId;

    public Task() {
    }

    public Task(String title, String projectId) {
        this.title = title;
        this.projectId = projectId;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
