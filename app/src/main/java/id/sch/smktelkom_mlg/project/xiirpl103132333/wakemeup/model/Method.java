package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;

/**
 * Created by asus on 20-Nov-16.
 */

public class Method extends DialogFragment {
    private static String[] items;
    private int selectedItem;
    private TextView restv;

    public Method() {
        super();
        selectedItem = -1;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        items = getResources().getStringArray(R.array.Method);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pilih Method").setSingleChoiceItems(R.array.Method, selectedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectedItem = i;
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(getActivity(), "index method yang terpilih " + selectedItem, Toast.LENGTH_SHORT).show();
                restv.setText(items[selectedItem]);
            }
        });

        return builder.create();
    }

    public void setResultTV(TextView resultTV) {
        this.restv = resultTV;
    }

    public String getResultString() {
        return items[selectedItem];
    }

    public int getResult() {
        return selectedItem;
    }

    public void setResult(int selectedItem) {
        this.selectedItem = selectedItem;
    }
}
