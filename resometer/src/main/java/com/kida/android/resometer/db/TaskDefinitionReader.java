package com.kida.android.resometer.db;


import android.provider.BaseColumns;

public final class TaskDefinitionReader {

    public TaskDefinitionReader(){

    }

    public static abstract class TaskDefinitionEntry implements BaseColumns{

        public static final String TABLE_NAME="task_def";
        public static final String COL_TASK_DEF_NAME="task_def_name";

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";




    }
}
