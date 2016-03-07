package com.kida.android.resometer;


import android.util.Log;

import com.kida.android.resometer.util.Day;
import com.kida.android.resometer.util.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskSummaryManager {

    private final static TaskSummaryManager taskSummaryManager = new TaskSummaryManager();

    //TODO to be populated from database
    private final static Map<Day,List<Task>>  userTasks = new HashMap<>();

    private TaskSummaryManager(){

    }

    public static TaskSummaryManager getInstance(){
        return taskSummaryManager;
    }


    public List<String> getTasks(final Day day) {

        List<Task> tasks = userTasks.get(day);
        if(tasks==null){
            return new ArrayList<>();
        }
        else {
            List<String> taskSummaries = new ArrayList<>(tasks.size());
            for(int i =0;i<tasks.size();i++){
               taskSummaries.add(tasks.get(i).toString());
            }
            return taskSummaries;
        }

    }

    public void updateTask(Day day, Task task){
        List<Task> tasks = userTasks.get(day);
        if(tasks==null){
            tasks = new ArrayList<>();
            userTasks.put(day,tasks);
        }
        tasks.add(task);


    }
}
