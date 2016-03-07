package com.kida.android.resometer.model;


/**
 * Created by mandar on 2/27/16.
 */
public class Task {

    private int id;
    private String name;

    public Task(){

    }
    public Task(int id, String name) {

        this.id=id;
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
