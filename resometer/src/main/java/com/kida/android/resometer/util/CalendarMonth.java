package com.kida.android.resometer.util;

import java.util.Calendar;

public class CalendarMonth {

    private long startOfMonth;
    private long endOfMonth;

    public CalendarMonth(long startOfMonth,long endOfMonth){
        this.startOfMonth = startOfMonth;
        this.endOfMonth = endOfMonth;
    }

    public long getStartOfMonth() {
        return startOfMonth;
    }

    public long getEndOfMonth() {
        return endOfMonth;
    }

   public CalendarMonth previous(){
       Calendar cal = Calendar.getInstance();
       cal.setTimeInMillis(endOfMonth);
       cal.add(Calendar.MONTH, -1);
       cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
       cal.set(Calendar.HOUR_OF_DAY, 23);
       endOfMonth = cal.getTimeInMillis();

       cal.set(Calendar.DATE, 1);
       cal.set(Calendar.HOUR_OF_DAY, 0);
       startOfMonth = cal.getTimeInMillis();
       return new CalendarMonth(startOfMonth,endOfMonth);
   }

    public CalendarMonth next(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(endOfMonth);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        endOfMonth = cal.getTimeInMillis();

        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        startOfMonth = cal.getTimeInMillis();
        return new CalendarMonth(startOfMonth,endOfMonth);
    }


}