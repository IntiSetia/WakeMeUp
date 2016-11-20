package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;

/**
 * Created by ASR on 18/11/2016.
 */

public class Hari extends DialogFragment {
    private static String[] items;
    ArrayList<String> list = new ArrayList<String>();
    private boolean[] checkedItem;

    public Hari() {
        super();
        checkedItem = new boolean[]{false, false, false, false, false, false, false};
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        items = getResources().getStringArray(R.array.Hari);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pilih Hari").setMultiChoiceItems(R.array.Hari, checkedItem, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (isChecked) {
                    list.add(items[which]);
                    checkedItem[which] = true;
                } else if (list.contains(items[which])) {
                    list.remove(items[which]);
                    checkedItem[which] = false;
                }
            }

        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selections = "";
                for (String ms : list) {
                    selections = selections + "\n" + ms;
                }
                Toast.makeText(getActivity(), "Hari Yang Dipilih : " + selections, Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}
