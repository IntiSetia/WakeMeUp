package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.tambahActivity;

/**
 * Created by asus on 20-Nov-16.
 */

public class Nada extends DialogFragment {
    private static String[] items;
    private EditText etNada;
    private int selectedItem;

    public Nada() {
        super();
        selectedItem = -1;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        items = getResources().getStringArray(R.array.Nada);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pilih Nada").setSingleChoiceItems(R.array.Nada, selectedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectedItem = i;

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(getActivity(), "index nada terpilih " + selectedItem, Toast.LENGTH_SHORT).show();
                ((tambahActivity) getActivity()).etNada.setText(items[selectedItem]);
            }
        });

        return builder.create();
    }

    public String getResultString() {
        return items[selectedItem];
    }

    public int getResult() {
        return selectedItem;
    }
}
