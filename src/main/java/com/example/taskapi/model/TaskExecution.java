package com.example.taskapi.model;
import lombok.Data;
import java.time.Instant;


@Data
public class TaskExecution {
    private String command;
    private String status;
    private String output;
    private Instant startTime;
    private Instant endTime;

    // Getters and Setters
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getOutput() {
        return output;
    }
    public void setOutput(String output) {
        this.output = output;
    }

    public Instant getStartTime() {
        return startTime;
    }
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
}