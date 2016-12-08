package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.AlarmAlertMath;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.adapter.StaticWakeLock;

/**
 * Created by euiko on 12/7/16.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mathAlarmServiceIntent = new Intent(
                context,
                AlarmReceiver.class);
        context.sendBroadcast(mathAlarmServiceIntent, null);

        StaticWakeLock.lockOn(context);
        Bundle bundle = intent.getExtras();
        long alarmId = bundle.getLong("alarmId");
        int method = bundle.getInt("method");

        Intent mathAlarmAlertActivityIntent;

        mathAlarmAlertActivityIntent = new Intent(context, AlarmAlertMath.class);

        mathAlarmAlertActivityIntent.putExtra("alarmId", alarmId);
        mathAlarmAlertActivityIntent.putExtra("code", AlarmAlertMath.ALARM_MATH_CALL_CODE);

        mathAlarmAlertActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(mathAlarmAlertActivityIntent);
    }

}