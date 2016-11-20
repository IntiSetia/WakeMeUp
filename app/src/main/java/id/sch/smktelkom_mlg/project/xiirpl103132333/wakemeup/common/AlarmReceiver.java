package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;

/**
 * Created by asus on 20-Nov-16.
 */

public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer player;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Toast.makeText(context, "Alarm aktif!", Toast.LENGTH_LONG).show();
        player = MediaPlayer.create(context, R.raw.air_raid);
        player.start();
    }

}
