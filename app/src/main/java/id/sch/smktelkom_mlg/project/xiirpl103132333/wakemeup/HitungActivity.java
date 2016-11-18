package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class HitungActivity extends AppCompatActivity {
    TextView Bil1, tvHasil;
    Button Jwb;
    EditText TulisJwb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung);

        Bil1 = (TextView) findViewById(R.id.textView1);
        TulisJwb = (EditText) findViewById(R.id.Jawaban);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        String randText = "";
        Random randGen = new Random();
        final int rando = randGen.nextInt(40);
        randText = Integer.toString(rando);

        String randText2 = "";
        Random randGen2 = new Random();
        final int rando2 = randGen2.nextInt(40);
        randText2 = Integer.toString(rando2);

        Bil1.setText(randText + " + " + randText2 + " = ");

        findViewById(R.id.Jawab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (doJawab(rando, rando2)) {
                    startActivity(new Intent(HitungActivity.this, Hitung2Activity.class));
                } else {
                    TulisJwb.setError("Jawaban salah");
                }
            }
        });
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
