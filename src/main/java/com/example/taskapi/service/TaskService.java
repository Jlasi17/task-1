package com.example.taskapi.service;

import com.example.taskapi.model.Task;
import com.example.taskapi.model.TaskExecution;
import com.example.taskapi.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repo;
    private final ExecutionService executor;

    public TaskService(TaskRepository repo, ExecutionService executor) {
        this.repo = repo;
        this.executor = executor;
    }

    public List<Task> getAllTasks() { return repo.findAll(); }

    public Optional<Task> getTaskById(String id) { return repo.findById(id); }

    public Task saveTask(Task task) { return repo.save(task); }

    public void deleteTask(String id) { repo.deleteById(id); }

    public List<Task> searchByName(String name) { return repo.findByNameContainingIgnoreCase(name); }

    public TaskExecution runTask(String id, String command) throws Exception {
        Task task = repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        String cmd = (command != null && !command.isBlank()) ? command : task.getCommand();

        TaskExecution exec = executor.runCommand(cmd);
        task.getTaskExecutions().add(exec);
        repo.save(task);
        return exec;
    }
}