package com.quadrolord.ejge.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Quadrowin on 05.07.2016.
 */
public class Dates {

    private static long mApplicationStartTime = getTimeInMillis();
    private static final SimpleDateFormat mDefault = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat mDefaultYmd = new SimpleDateFormat("yyyy-MM-dd");

    public static long getApplicationStartTime() {
        return mApplicationStartTime;
    }

    public static long getTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static String now() {
        return mDefault.format(new Date());
    }

    public static String format(Date date) {
        return mDefault.format(date);
    }

    public static String formatYmd(Date date) {
        return mDefaultYmd.format(date);
    }

}
