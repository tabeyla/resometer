package com.kida.android.resometer.util;

import java.io.Serializable;

public class Day implements Serializable {

    private final int year;
    private final int month;
    private final int dayOfMonth;
    private final int hashCode;


    public Day(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;

        hashCode = Integer.valueOf(this.year).hashCode()*Integer.valueOf(this.month).hashCode()*Integer.valueOf(this.dayOfMonth).hashCode();
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    @Override
    public boolean equals(Object o) {
       if(o==null || o.getClass()!=Day.class) return false;
       else if(this == o) return true;
        Day that = (Day)o;
        return this.year==that.year&&this.month==that.month&&this.dayOfMonth==that.dayOfMonth;

    }


    @Override
    public String toString() {
        return "yyyyMMdd="+yyyyMMdd();
    }

    public String yyyyMMdd(){
        return ""+year+(Math.log10(month)+1!=2?"0"+month:month)+dayOfMonth;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }
}
