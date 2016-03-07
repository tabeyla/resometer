package com.kida.android.resometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.kida.android.resometer.util.CalendarMonth;
import com.kida.android.resometer.util.Day;
import com.kida.android.resometer.util.ResoMeterUtil;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends Activity {

    private CalendarView calendarView;
    public static final String CALENDAR_DAY = "CALENDAR_DAY";
    public static final String EDITABLE = "EDITABLE";
    private CalendarMonth calendarMonth;
    GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar();
        calendar();

        listeners();


    }

    private void listeners() {

        dateChangeListener();
        gestureListener();
    }

    private void gestureListener() {
        gestureDetector = new GestureDetector(this, new GestureListener());
    }


    private void toolbar() {
        addMenuToToolbar();
        setToolbarTitle();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

 public void onClickMenuItem(MenuItem item){

     switch(item.getItemId()){
         case R.id.menu_item_task_manager:
             Intent intent = new Intent(CalendarActivity.this,TaskManagerActivity.class);
             startActivity(intent);
             break;
         case R.id.menu_item_task_summary:
             Toast.makeText(getApplicationContext(), "Nothing to see here, yet!!!", Toast.LENGTH_SHORT).show();
             break;
         default:
             return;

     }

 }
    private void calendar() {
        calendarMonth = ResoMeterUtil.getCurrentMonth();

        updateCalendarView();
    }


    private void addMenuToToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

       toolbar.inflateMenu(R.menu.menu_main);

    }


    private void setToolbarTitle() {

        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText(dayOfYear());

    }

    private String dayOfYear() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        int max = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        return "Day "+day+"/"+max;
    }

    private void dateChangeListener() {

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Date today = new Date();
                Date input = new Date(view.getDate());

                if (input.after(today)) return;

                Intent intent = new Intent(CalendarActivity.this, TaskSummaryActivity.class);

                //month starts with 0
                intent.putExtra(CALENDAR_DAY, new Day(year, month + 1, dayOfMonth));


                if (input.before(today)) {
                    intent.putExtra(EDITABLE, false);
                } else {
                    intent.putExtra(EDITABLE, true);
                }


                startActivity(intent);

            }
        });
    }


    private void updateCalendarView() {

        calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setMaxDate(calendarMonth.getEndOfMonth());
        calendarView.setMinDate(calendarMonth.getStartOfMonth());
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        public void onSwipeRight() {
            calendarMonth = calendarMonth.previous();
            calendarView.setMaxDate(calendarMonth.getEndOfMonth());
            calendarView.setMinDate(calendarMonth.getStartOfMonth());

        }

        public void onSwipeLeft() {


            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            long endOfMonth = cal.getTimeInMillis();

            if(endOfMonth== calendarMonth.getEndOfMonth()) return;
            calendarMonth = calendarMonth.next();

            calendarView.setMaxDate(calendarMonth.getEndOfMonth());
            calendarView.setMinDate(calendarMonth.getStartOfMonth());
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }

       /* @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            // Trigger the touch event on the calendar
            Log.d("-----", "onSingleTapConfirmed");
            calendarView.onTouchEvent(event);
            return super.onSingleTapUp(event);
        }
*/
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    result = true;
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }}




}
