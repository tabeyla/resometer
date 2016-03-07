package com.kida.android.resometer.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ResoMeterUtil {

    //This is a master list of currently available tasks
    //TODO add to db table
    private static List<Task> availableTasks = new ArrayList<>();
    static {

        availableTasks.add(new Task( "Meditation", 20));
        availableTasks.add(new Task( "Java/Development", 60));
        availableTasks.add(new Task( "Yoga Class", 60));

    }

    public static List<Task> getAvailableTasks(){
        return Collections.unmodifiableList(availableTasks);
    }

    public static String [] getAvailableTaskNames(){
        String [] taskNames = new String[availableTasks.size()];
        for(int i =0;i< availableTasks.size();i++){
            taskNames[i] = availableTasks.get(i).getName();
        }
        return taskNames;
    }

    public static void addNewTask(String task){
        availableTasks.add(new Task(task,20));
    }

    public static CalendarMonth getCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 23);//not sure this is needed
        long endOfMonth = calendar.getTimeInMillis();

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        long startOfMonth = calendar.getTimeInMillis();

        return new CalendarMonth(startOfMonth,endOfMonth);
    }
}
