package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common;

import android.app.Application;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.utils.FontLoader;

public class GameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FontLoader.loadFonts(this);

    }
}
