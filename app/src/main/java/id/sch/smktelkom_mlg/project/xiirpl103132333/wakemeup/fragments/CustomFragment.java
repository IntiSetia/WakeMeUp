package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common.Shared;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.CustomStartEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.themes.Theme;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.themes.Themes;

/**
 * Created by euiko on 10/25/16.
 */

public class CustomFragment extends BaseFragment {

    private Button mulai;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.custom_layout, container, false);

        mulai = (Button) view.findViewById(R.id.buttonCustom);
        final int diff = 5;
        final Theme theme = Themes.createAnimalsTheme();

        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new CustomStartEvent(diff, theme));
            }
        });

        return view;
    }
}
