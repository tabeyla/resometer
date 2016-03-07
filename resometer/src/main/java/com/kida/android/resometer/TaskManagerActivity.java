package com.kida.android.resometer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kida.android.resometer.R;
import com.kida.android.resometer.util.ResoMeterUtil;

public class TaskManagerActivity extends AppCompatActivity {

    EditText eid;
    Button addTaskButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_manager);

        eid = (EditText)findViewById(R.id.input_task_name);

        addTaskButton = (Button)findViewById(R.id.btn_save_new_task);



        eid.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
                String input = s.toString().trim();
                for(String task : ResoMeterUtil.getAvailableTaskNames()){
                    if(task.equalsIgnoreCase(input)){
                        Toast toast = Toast.makeText(getApplicationContext(), "Task already exists", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                }
            }
        });


    }

    public void addTask(View view){
        String task = eid.getText().toString();
        ResoMeterUtil.addNewTask(task);
        finish();

    }



}
