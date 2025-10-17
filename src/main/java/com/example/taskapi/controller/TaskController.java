package com.example.taskapi.controller;

import com.example.taskapi.model.Task;
import com.example.taskapi.model.TaskExecution;
import com.example.taskapi.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable String id) {
        return service.getTaskById(id);
    }

    @PutMapping
    public Task putTask(@RequestBody Task task) {
        return service.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        service.deleteTask(id);
    }

    @GetMapping("/search")
    public List<Task> searchByName(@RequestParam String name) {
        return service.searchByName(name);
    }

    @PutMapping("/{id}/executions")
    public TaskExecution runExecution(@PathVariable String id, @RequestBody(required = false) Task body) throws Exception {
        String command = (body != null) ? body.getCommand() : null;
        return service.runTask(id, command);
    }
}