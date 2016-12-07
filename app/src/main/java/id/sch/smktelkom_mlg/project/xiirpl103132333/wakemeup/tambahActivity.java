package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup;

import android.app.Dialog;
import android.app.TimePickerDialog;
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
import java.util.List;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.Hari;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.Method;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.Nada;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model.dbAlarm;

import static android.app.AlertDialog.THEME_HOLO_LIGHT;

public class tambahActivity extends AppCompatActivity {

    static final int TIME_DIALOG_ID = 1;
    public static String day[] = {
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };
    // variables to save user selected date and time
    public int hour, minute;
    public Button btnHari, btnSetAlarm;
    public EditText etWaktu, etMemo, etNada, etMethod;
    SeekBar seekBar1;
    private Hari hariDialog;
    private Nada nadaDialog;
    private Method methodDialog;
    // declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
    private int mHour, mMinute;
    private Boolean isEdit;
    private long alarmId;
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

    public static boolean[] getBoolDays(String days) {
        boolean[] res = new boolean[]{false, false, false, false, false, false, false};

        for (int i = 0; i < days.length() - 1; i++) {
            res[i] = days.charAt(i) == '1';
        }

        return res;
    }

    public static int searchRingtone(String song, String[] sArr) {
        int res;
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i].equals(song)) return i;
        }
        return -1;
    }

    public static String getHTMLDays(String days) {
        String res = "";
        for (int i = 0; i < days.length(); i++) {
            res += days.charAt(i) == '1' ? "<b><font color=#444444>" + day[i].charAt(0) + " </font></b>" : "<font color=#AAAAAA>" + day[i].charAt(0) + " </font>";
        }

        return res;
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

        hariDialog = new Hari();
        nadaDialog = new Nada();
        nadaDialog.setArray(getResources().getStringArray(R.array.Nada));
        methodDialog = new Method();
        methodDialog.setArray(getResources().getStringArray(R.array.Method));
        methodDialog.setResultTV(etMethod);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.alarmId = bundle.getLong("id");
            if (bundle.getInt("code") == MainActivity.EDIT) {
                this.isEdit = true;
                dbAlarm db = new dbAlarm();
                setTitle(R.string.edit_title);
                List<dbAlarm> data = db.getDataAtId(alarmId);
                dbAlarm selectedData = data.get(0);
                boolean[] checkedDays = getBoolDays(selectedData.days);
                hariDialog.setCheckedItem(checkedDays);
                methodDialog.setResult(selectedData.method);
                nadaDialog.setResult(searchRingtone(selectedData.ringtone, nadaDialog.getArray()));

                etWaktu.setText(selectedData.hours);
                etMemo.setText(selectedData.memo);
                etNada.setText(nadaDialog.getResultString());
                etMethod.setText(methodDialog.getResultString());
                seekBar1.setProgress(selectedData.level);
            } else {
                setTitle(R.string.add_title);
                this.isEdit = false;
            }
        } else {
            setTitle(R.string.add_title);
            this.isEdit = false;
        }
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
                int l = seekBar1.getProgress();
                dbAlarm db = new dbAlarm();
                if (isEdit) {
                    db.editAlarm(alarmId, etWaktu.getText().toString(), hariDialog.getNumberRes(), nadaDialog.getResultString(),
                            methodDialog.getResult(), l, etMemo.getText().toString());
                } else {
                    long id = alarmId + 1;
                    db.saveAlarm(id, etWaktu.getText().toString(), hariDialog.getNumberRes(), nadaDialog.getResultString(),
                            methodDialog.getResult(), l, etMemo.getText().toString(), true);
                }

                onBackPressed();
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
                return new TimePickerDialog(this, THEME_HOLO_LIGHT, mTimeSetListener, mHour, mMinute, true);

        }
        return null;
    }


}
