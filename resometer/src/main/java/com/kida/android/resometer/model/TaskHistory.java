package com.kida.android.resometer.model;

/**
 * Created by mandar on 3/2/16.
 */
public class TaskHistory {


    private int id;


    private int taskDay;
    private String name;


    public String getName() {
        return name;
    }

    public int getTaskDay() {
        return taskDay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaskDay(int taskDay) {
        this.taskDay = taskDay;
    }

    public int getId() {
        return id;
    }
}
