package com.kida.android.resometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kida.android.resometer.util.Day;
import com.kida.android.resometer.util.Task;

import java.util.List;


public class TaskSummaryActivity extends Activity {

    public static final int REQUEST_CODE = 12;
    private Day day ;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_summary);

        Intent intent = getIntent();
        day = (Day)intent.getSerializableExtra(CalendarActivity.CALENDAR_DAY);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        boolean editable = intent.getBooleanExtra(CalendarActivity.EDITABLE,false);
        fab.setVisibility(editable ? View.VISIBLE : View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskSummaryActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });


        //Add the label yyyyMMdd
        EditText taskDayView = (EditText)findViewById(R.id.task_summary_label);
        taskDayView.setText(day.yyyyMMdd());

     //Add a list of tasks for the day
        //TODO add current date
    List<String> values = TaskSummaryManager.getInstance().getTasks(day);

        ListView taskSummryListView = (ListView)findViewById(R.id.task_summary_list);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        taskSummryListView.setAdapter(adapter);

        if(values.size()==0) {
            Toast.makeText(getApplicationContext(), "No task exist for the day", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra(AddTaskActivity.NEW_TASK)) {
                Task task = (Task)data.getSerializableExtra(AddTaskActivity.NEW_TASK);
                TaskSummaryManager.getInstance().updateTask(day,task);
                adapter.clear();
                adapter.addAll(TaskSummaryManager.getInstance().getTasks(day));
                adapter.notifyDataSetChanged();

            }

        }

    }

}
