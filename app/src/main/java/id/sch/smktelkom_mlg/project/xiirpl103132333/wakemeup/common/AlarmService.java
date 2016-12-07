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
    public int counter = 0;
    public int timeIndex;
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
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        long nextTimeMilis = getNextAlarm();
        if (nextTimeMilis != -1) {
            List<dbAlarm> allDb = dbAlarm.getAll();
            Log.i("service", "initializeTimerTask: " + Calendar.getInstance().getTimeInMillis() + " " + nextTimeMilis);
            Intent myIntent = new Intent(MainActivity.getCtx(), AlarmBroadcastReceiver.class);
            myIntent.putExtra("alarm", allDb.get(timeIndex).hours);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.getCtx(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            AlarmManager alarmManager = (AlarmManager) MainActivity.getCtx().getSystemService(Context.ALARM_SERVICE);

            alarmManager.set(AlarmManager.RTC_WAKEUP, nextTimeMilis, pendingIntent);
        }
    }

    public long getNextAlarm() {
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
            int checkDay = getNextDay(thisDay, alarm.get(i).days);
            if (checkDay < curDay) {
                curDay = checkDay;
            }
        }

        int dayCharI = thisDay + curDay;
        if (dayCharI > 6) dayCharI -= 7;
        alarm = dbAlarm.getAllDayOn(dayCharI + 1);

        Log.i("service", "getNextAlarm: " + thisDay + " " + alarm.size());

        for (int i = 0; i < alarm.size(); i++) {
            String[] currentTimeS = alarm.get(i).hours.split(" : ");
            int curHourMinTime = Integer.parseInt(currentTimeS[0]) * 60 + Integer.parseInt(currentTimeS[1]);
            if (curHourMinTime > thisHourMinTime) {
                long thisTimeMilis = c.getTimeInMillis();
                this.timeIndex = i;
                return thisTimeMilis + ((curHourMinTime * 60 + (dayCharI * 86400)) * 1000);
            }
        }

        return res;
    }

    public int getNextDay(int startDay, String days) {
        int j = 0;
        for (int i = startDay; j < 7; j++) {
            if (i > 6) i -= 7;
            if (days.charAt(i) == '1') {
                return j;
            }
            i++;
        }
        return -1;
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