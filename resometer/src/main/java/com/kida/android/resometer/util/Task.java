package com.kida.android.resometer.util;

import java.io.Serializable;

/**
 * Created by mandar on 2/15/16.
 */
public class Task implements Serializable{


    private final String name;
    private final int duration;

    public Task(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }



    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
       if(o==null || o.getClass()!=Task.class) return false;
        if(this == o) return true;
        Task that = (Task)o;
        return this.name==that.name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name+" "+duration;
    }
}
