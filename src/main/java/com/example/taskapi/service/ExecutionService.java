package com.example.taskapi.service;

import com.example.taskapi.model.TaskExecution;
import com.example.taskapi.util.CommandValidator;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class ExecutionService {

    private final String podName = "my-task-pod"; // Replace with your pod name or make dynamic

    public TaskExecution runCommand(String command) throws Exception {
        if (!CommandValidator.isSafe(command)) {
            throw new IllegalArgumentException("Unsafe command!");
        }

        Instant start = Instant.now(); // start time

        // Run command inside Kubernetes pod using kubectl exec
        ProcessBuilder builder = new ProcessBuilder(
            "kubectl", "exec", podName, "--", "sh", "-c", command
        );
        builder.redirectErrorStream(true);
        Process process = builder.start();

        String output;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            output = reader.lines().collect(Collectors.joining("\n"));
        }

        int exitCode = process.waitFor();
        String status = (exitCode == 0) ? "SUCCESS" : "FAILED";

        Instant end = Instant.now(); // end time

        // Create TaskExecution with all fields
        TaskExecution execution = new TaskExecution();
        execution.setCommand(command);
        execution.setStatus(status);
        execution.setOutput(output);
        execution.setStartTime(start);
        execution.setEndTime(end);

        return execution;
    }
}