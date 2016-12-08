package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.MainActivity;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.dbAlarm;

/**
 * Created by fabio on 30/01/2016.
 */
public class AlarmService extends Service {
    public static int timeIndex;
    public int counter = 0;
    long oldTime = 0;
    private Timer timer;
    private TimerTask timerTask;
    private dbAlarm db;

    public AlarmService(Context applicationContext) {
        super();
        db = new dbAlarm();
        Log.i("HERE", "here I am!");
    }

    public AlarmService() {
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public static void initializeTimerTask() {
        long nextTimeMilis = getNextAlarm();
        if (nextTimeMilis != -1) {
            List<dbAlarm> allDb = dbAlarm.getAll();
            Log.i("service", "initializeTimerTask: " + System.currentTimeMillis() + " " + nextTimeMilis);

            Intent myIntent = new Intent(MainActivity.getCtx(), AlarmBroadcastReceiver.class);
            myIntent.putExtra("alarmId", allDb.get(timeIndex).getId());
            myIntent.putExtra("method", allDb.get(timeIndex).method);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.getCtx(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            AlarmManager alarmManager = (AlarmManager) MainActivity.getCtx().getSystemService(Context.ALARM_SERVICE);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextTimeMilis, pendingIntent);
        }
    }

    public static long getNextAlarm() {
        long res = -1;
        Calendar c = Calendar.getInstance();
        int thisDay = c.get(Calendar.DAY_OF_WEEK) - 1;
        int thisHour = c.get(Calendar.HOUR_OF_DAY);
        int thisMinute = c.get(Calendar.MINUTE);
        int thisHourMinTime = thisHour * 60 + thisMinute;
        List<dbAlarm> alarm = dbAlarm.getAllEnabled();
        List<Integer> thisDayAlarmI;

        int curDay = 99;

        for (int i = 0; i < alarm.size(); i++) {
            int checkDay = getNextDay(thisDay, alarm.get(i).days, thisHourMinTime, alarm.get(i).hours);
            if (checkDay < curDay) {
                curDay = checkDay;
            }
        }

        int dayCharI = thisDay + curDay;
        if (dayCharI > 6) dayCharI -= 7;
        alarm = dbAlarm.getAllDayOn(dayCharI + 1);

        Log.i("service", "getNextAlarm: " + thisDay + " " + dayCharI + " " + alarm.size() + " " + curDay);

        for (int i = 0; i < alarm.size(); i++) {
            String[] currentTimeS = alarm.get(i).hours.split(" : ");
            int curHourMinTime = Integer.parseInt(currentTimeS[0]) * 60 + Integer.parseInt(currentTimeS[1]);
            if (curHourMinTime > thisHourMinTime || curDay > 0) {
                long thisTimeMilis = (System.currentTimeMillis() / 60000) * 60000;
                long curNextAlarm = curHourMinTime - thisHourMinTime;
                AlarmService.timeIndex = i;
                return thisTimeMilis + ((curNextAlarm * 60 + (curDay * 86400)) * 1000);
            }
        }

        return res;
    }

    public static int getNextDay(int startDay, String days, long nowMinute, String hours) {
        int j = 0;
        String[] sepHours = hours.split(" : ");
        long curHourMinTime = Integer.parseInt(sepHours[0]) * 60 + Integer.parseInt(sepHours[1]);
        for (int i = startDay; j < 7; j++) {
            if (i > 6) i -= 7;
            char curChar = days.charAt(i);
            if (j > 0) {
                if (curChar == '1') {
                    return j;
                }
            } else {
                if (curChar == '1' && curHourMinTime > nowMinute) {
                    return j;
                }
            }
            i++;
        }
        j = 99;
        return j;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startTimer();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
        restartService();
    }

    public void startTimer() {
        //set a new Timer
        //timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second
        //.schedule(timerTask, 1000, 1000); //
    }

    /**
     * not needed
     */
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void restartService() {
        Intent broadcastIntent = new Intent("RestartSensor");
        sendBroadcast(broadcastIntent);
        stoptimertask();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        restartService();
        Log.i("EXIT", "ontaskremoved!");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        restartService();
        Log.i("EXIT", "onLowMemory!");
    }
}