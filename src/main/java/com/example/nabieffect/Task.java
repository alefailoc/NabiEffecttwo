package com.example.nabieffect;

import javafx.scene.paint.Color;

import java.time.LocalDate;

public class Task {
    String task;
    String taskDetails;
    LocalDate dueDate;
    double expectedTime;
    Color color;

    public Task(String task, String taskDetails, LocalDate dueDate, double expectedTime) {
        this.task = task;
        this.taskDetails = taskDetails;
        this.dueDate = dueDate;
/** if (dueDate = dueDate - 1 -->(previous day)){
 * this.color = red
   }else if (dueDate = dueDate - 5 -->(a week before)){
   this.color = orange
 }

*/
        this.expectedTime = expectedTime;
        if(expectedTime<1){
            this.color = Color.GREEN;
        }else if(expectedTime<2){
            this.color = Color.ORANGE;
        }else{
            this.color = Color.RED;
        }
    }

    // get method = accessor
    // set method = mutator
    public LocalDate getDueDate() {
        return dueDate;
    }

    public Color getColor() {
        return color;
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
