package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.MainActivity;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common.Shared;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.CustomStartEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.themes.Theme;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.themes.Themes;

/**
 * Created by euiko on 10/25/16.
 */

public class CustomFragment extends BaseFragment {

    public int diff;
    private Button mulai;

    public CustomFragment newInstance(int diff) {
        CustomFragment cf = new CustomFragment();

        Bundle args = new Bundle();
        args.putInt(MainActivity.LEVEL, diff);
        cf.setArguments(args);

        return cf;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.custom_layout, container, false);

        mulai = (Button) view.findViewById(R.id.buttonCustom);
        int diff = getArguments().getInt(MainActivity.LEVEL, 1);
        final Theme theme = Themes.createAnimalsTheme();

        Shared.eventBus.notify(new CustomStartEvent(diff, theme));
        return view;
    }
}
