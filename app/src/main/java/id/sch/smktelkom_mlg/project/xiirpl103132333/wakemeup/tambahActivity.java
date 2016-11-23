package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.Hari;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.Method;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.Nada;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.dbAlarm;

public class tambahActivity extends AppCompatActivity {

    static final int TIME_DIALOG_ID = 1;
    // variables to save user selected date and time
    public int hour, minute;
    public Button btnHari, btnSetAlarm;
    public EditText etWaktu, etMemo, etNada, etMethod;
    TimePickerDialog alarm_timepicker;
    TimePicker myTimePicker;
    AlarmManager alarm_manager;
    Context context;
    Calendar calendar;
    SeekBar seekBar1;
    // declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
    private int mHour, mMinute;

    // Register  TimePickerDialog listener
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        // the callback received when the user "sets" the TimePickerDialog in the dialog
        public void onTimeSet(TimePicker view, int hourOfDay, int min) {
            String hour = String.valueOf(hourOfDay);
            String minute = String.valueOf(min);
            hour = hour.length() <= 1 ? "0" + hour : hour;
            minute = minute.length() <= 1 ? "0" + minute : minute;

            // Set the Selected Date in Select date Button
            etWaktu.setText(hour + " : " + minute);
        }
    };


    public tambahActivity() {
        // Assign current Date and Time Values to Variables
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        btnHari = (Button) findViewById(R.id.buttonHari);
        etWaktu = (EditText) findViewById(R.id.editTextWaktu);
        etMemo = (EditText) findViewById(R.id.editTextMemo);
        etNada = (EditText) findViewById(R.id.editTextNada);
        etMethod = (EditText) findViewById(R.id.editTextMethod);
        btnSetAlarm = (Button) findViewById(R.id.buttonSet);


        final Hari hariDialog = new Hari();
        final Nada nadaDialog = new Nada();
        final Method methodDialog = new Method();

        int Hournow = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        final int jamz = Hournow - hour;
        int Minnow = Calendar.getInstance().get(Calendar.MINUTE);
        final int minz = Minnow - mMinute;

        etMemo.clearFocus();

        // Set ClickListener on btnSelectTime
        etWaktu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Show the TimePickerDialog
                showDialog(TIME_DIALOG_ID);
            }
        });

        etNada.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    nadaDialog.show(getSupportFragmentManager(), "multi-demo");
                    v.clearFocus();
                }
            }
        });

        etMethod.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    methodDialog.show(getSupportFragmentManager(), "multi-demo");
                    v.clearFocus();
                }
            }
        });

        findViewById(R.id.buttonSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    String a = etWaktu.getText().toString();
                    Toast.makeText(getApplicationContext(), "Alarm Akan Berbunyi " + a, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(tambahActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.buttonHari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hariDialog.show(getSupportFragmentManager(), "multi-demo");
            }
        });

        seekBar1 = (SeekBar) findViewById(R.id.seekBar);
        seekBar1.setMax(4);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (progressChanged == 0) {
                    Toast.makeText(tambahActivity.this, "Level: Very Easy",
                            Toast.LENGTH_SHORT).show();
                } else if (progressChanged == 1) {
                    Toast.makeText(tambahActivity.this, "Level: Easy",
                            Toast.LENGTH_SHORT).show();
                } else if (progressChanged == 2) {
                    Toast.makeText(tambahActivity.this, "Level: Medium",
                            Toast.LENGTH_SHORT).show();
                } else if (progressChanged == 3) {
                    Toast.makeText(tambahActivity.this, "Level: Hard",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(tambahActivity.this, "Level: Very Hard",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(tambahActivity.this, hariDialog.getNumberRes(), Toast.LENGTH_SHORT).show();
                int l = seekBar1.getProgress();
                dbAlarm db = new dbAlarm();
                db.saveAlarm(etWaktu.getText().toString(), hariDialog.getNumberRes(), nadaDialog.getResultString(), methodDialog.getResult(), l, etMemo.getText().toString());
            }
        });

    }

    private boolean isValid() {
        boolean valid = true;
        String waktu = etWaktu.getText().toString();
        String nada = etNada.getText().toString();
        String method = etMethod.getText().toString();

        if (waktu.isEmpty()) {
            etWaktu.setError("Anda belum mengatur waktu");
            valid = false;
        } else {
            etWaktu.setError(null);
        }
        if (nada.isEmpty()) {
            etNada.setError("Pilih ringtone terlebih dahulu");
            valid = false;
        } else {
            etNada.setError(null);
        }
        if (method.isEmpty()) {
            etMethod.setError("Pilih metode terlebih dahulu");
            valid = false;
        } else {
            etMethod.setError(null);
        }

        return valid;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            // create a new TimePickerDialog with values you want to show
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, false);

        }
        return null;
    }


}
