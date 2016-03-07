package com.kida.android.resometer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.kida.android.resometer.util.ResoMeterUtil;
import com.kida.android.resometer.util.Task;

public class AddTaskActivity extends AppCompatActivity {

    public static final String NEW_TASK = "NEW_TASK";
    private Spinner spinner;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        spinner = (Spinner) findViewById(R.id.tasks_spinner);
        editText = (EditText)findViewById(R.id.input_task_duration);

        String [] spinnerArray = ResoMeterUtil.getAvailableTaskNames();
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerArray);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }

    public void addTask(View view){
       Intent intent = getIntent();
        String taskName = spinner.getSelectedItem().toString();
        Integer duration = Integer.parseInt(editText.getText().toString());
        intent.putExtra(NEW_TASK,new Task(taskName,duration));
        setResult(RESULT_OK,intent);
        finish();
    }

}
