package com.kida.android.resometer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kida.android.resometer.model.Task;
import com.kida.android.resometer.model.TaskHistory;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "taskManager";

    // Table Names
    private static final String TABLE_TASK = "task";
    private static final String TABLE_TASK_HISTORY= "task_history";

    // Common column names
    private static final String ID = "id";

    // Task Table - column nmaes
    private static final String COL_TASK_NAME = "name";

    // TaskHistory Table - column names
    private static final String COL_TASK_HISTORY_TASK_DAY = "taskDay";

    // Table Create Statements
    private static final String CREATE_TABLE_TASK = "CREATE TABLE "
            + TABLE_TASK + "(" + ID + " INTEGER PRIMARY KEY," + COL_TASK_NAME
            + " TEXT)";

    private static final String CREATE_TABLE_TASK_HISTORY = "CREATE TABLE " + TABLE_TASK_HISTORY
            + "(" + ID + " INTEGER PRIMARY KEY,"
            + COL_TASK_HISTORY_TASK_DAY + " INTEGER,"
            + COL_TASK_NAME + " TEXT )";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public long addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TASK_NAME, task.getName());

        long id = db.insert(TABLE_TASK, null, values);

        return id;
    }

    public long addTaskHistory(TaskHistory taskHistory){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TASK_NAME, taskHistory.getName());
        values.put(COL_TASK_HISTORY_TASK_DAY, taskHistory.getTaskDay());

        long id = db.insert(TABLE_TASK, null, values);

        return id;
    }

    public void deleteTaskHistory(TaskHistory taskHistory){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TASK_HISTORY, ID + "=" + taskHistory.getId(), null);
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASK ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Task td = new Task();
                td.setId(c.getInt((c.getColumnIndex(ID))));
                td.setName((c.getString(c.getColumnIndex(COL_TASK_NAME))));

                // adding to todo list
                tasks.add(td);
            } while (c.moveToNext());
        }

        return tasks;
    }

    public List<TaskHistory> getTaskHistory(int day){
        List<TaskHistory> taskHistories = new ArrayList<TaskHistory>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASK_HISTORY + " WHERE "+COL_TASK_HISTORY_TASK_DAY+"="+day;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                TaskHistory td = new TaskHistory();
                td.setId(c.getInt((c.getColumnIndex(ID))));
                td.setName((c.getString(c.getColumnIndex(COL_TASK_NAME))));
                td.setTaskDay(day);

                // adding to todo list
                taskHistories.add(td);
            } while (c.moveToNext());
        }

        return taskHistories;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_TASK);
        db.execSQL(CREATE_TABLE_TASK_HISTORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL(CREATE_TABLE_TASK);
        db.execSQL(CREATE_TABLE_TASK_HISTORY);

        // create new tables
        onCreate(db);
    }
}