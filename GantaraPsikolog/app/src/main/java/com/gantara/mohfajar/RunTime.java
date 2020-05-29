package com.gantara.mohfajar;

import android.util.Log;

import java.util.Calendar;

public class RunTime {
    public static void logTime(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        int milis = c.get(Calendar.MILLISECOND);
        Log.d("TAG", "This is the time "
                +year+ "-"
                +month+ "-"
                +day+ " "
                +hour+ ":" +minute+ ":" +second+ "." +milis);
    }
}
