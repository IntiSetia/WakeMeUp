package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HitungActivity extends AppCompatActivity {
    private static final String TAG = "LivecycleTag";
    TextView Bil1, tvHasil;
    Button Jwb, pause;
    EditText TulisJwb;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_math);
        Bil1 = (TextView) findViewById(R.id.textViewSoal);
        TulisJwb = (EditText) findViewById(R.id.EditTextJawab);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        ImageButton pause = (ImageButton) findViewById(R.id.buttonPause);

        mediaPlayer = MediaPlayer.create(this, R.raw.air_raid);
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
        if (mediaPlayer != null)
            mediaPlayer.release();
    }
    private boolean doJawab(int bil1, int bil2) {
        int a = bil1;
        int b = bil2;
        int jwb = TulisJwb.getText().toString().isEmpty() ? 0 : Integer.parseInt(TulisJwb.getText().toString());
        int c = a + b;
        if (jwb == c) {
            Toast.makeText(getApplicationContext(), "Selamat, Anda Benar!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Anda Salah!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
