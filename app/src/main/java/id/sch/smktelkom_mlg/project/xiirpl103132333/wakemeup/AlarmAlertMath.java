package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common.MathMethod;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.engine.HomeKeyLocker;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.Nada;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.dbAlarm;

public class AlarmAlertMath extends AppCompatActivity {
    public final static int ALARM_MATH_CALL_CODE = 1325;
    public static final String TAG = "AlarmAlertmMath";
    public long alarmId;
    public MathMethod math;
    TextView tvJam, tvSoal;
    EditText etJawab;
    Button bOK;
    dbAlarm db;
    List<dbAlarm> mAlarmList;
    MediaPlayer mediaPlayer;
    Nada nada;
    private HomeKeyLocker mHomeKeyLocker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        mHomeKeyLocker = new HomeKeyLocker();
        mHomeKeyLocker.lock(this);
        setContentView(R.layout.activity_popup_math);

        db = new dbAlarm();
        tvJam = (TextView) findViewById(R.id.textViewJam);
        tvSoal = (TextView) findViewById(R.id.textViewSoal);
        etJawab = (EditText) findViewById(R.id.EditTextJawab);
        bOK = (Button) findViewById(R.id.buttonJawab);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getInt("code") == ALARM_MATH_CALL_CODE) {
                alarmId = bundle.getLong("alarmId");
                mAlarmList = db.getDataAtId(alarmId);
                dbAlarm selectedAlarm = mAlarmList.get(0);

                nada = new Nada();
                String[] nadaArr = getResources().getStringArray(R.array.Nada);
                nada.setArray(nadaArr);
                nada.setSongArray(getResources().getStringArray(R.array.NadaValue));
                tvJam.setText(selectedAlarm.hours);
                math = new MathMethod(tvSoal, selectedAlarm.level);

                bOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (math.checkResult(etJawab.getText().toString().isEmpty() ? 0 : Integer.parseInt(etJawab.getText().toString()))) {
                            Toast.makeText(AlarmAlertMath.this, "True", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AlarmAlertMath.this, "False", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                nada.setResult(tambahActivity.searchRingtone(selectedAlarm.ringtone, nadaArr));
                int songId = MainActivity.getResId(nada.getSongResult(nada.getResult()), R.raw.class);

                mediaPlayer = MediaPlayer.create(this, songId);
                mediaPlayer.setLooping(true);

                findViewById(R.id.buttonPause).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mediaPlayer.setVolume(0, 0);

                    }
                });
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.setStreamVolume(
                        AudioManager.STREAM_MUSIC,
                        am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                        0);
                Log.d(TAG, "onCreate: ");
            } else {
                Log.e("illegal call", "onCreate: wrong call code");
            }
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
        mediaPlayer.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

        mHomeKeyLocker.unlock();
        mHomeKeyLocker = null;
        if (mediaPlayer != null)
            mediaPlayer.release();
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }

}
