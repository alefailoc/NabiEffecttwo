package com.example.nabieffect;

import java.time.LocalDate;

public class Task {
    String task;
    String taskDetails;
    LocalDate dueDate;
    double expectedTime;

    public Task(String task, String taskDetails, LocalDate dueDate, double expectedTime) {
        this.task = task;
        this.taskDetails = taskDetails;
        this.dueDate = dueDate;
        this.expectedTime = expectedTime;
    }

    // get method = accessor
    // set method = mutator
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public double getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(int expectedTime) {
        this.expectedTime = expectedTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task='" + task + '\'' +
                ", taskDetails='" + taskDetails + '\'' +
                ", dueDate=" + dueDate +
                ", expectedTime=" + expectedTime +
                '}';
    }
}
