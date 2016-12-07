package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fabio on 30/01/2016.
 */
public class AlarmService extends Service {
    public int counter = 0;
    long oldTime = 0;
    private Timer timer;
    private TimerTask timerTask;

    public AlarmService(Context applicationContext) {
        super();
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
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 1000, 1000); //
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Log.i("in timer", "in timer ++++  " + (counter++));
                if (counter == 10) {
                    //Intent i = new Intent(getBaseContext(), MainActivity.class);
                    //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //startActivity(i);
                }
            }
        };
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