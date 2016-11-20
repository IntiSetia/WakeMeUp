package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.MainActivity;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.engine.Engine;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.engine.ScreenController;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.engine.ScreenController.Screen;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.EventBus;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.BackGameEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.ui.PopupManager;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.utils.Utils;

public class GameMethod extends FragmentActivity {

    private ImageView mBackgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Shared.context = getApplicationContext();
        Shared.engine = Engine.getInstance();
        Shared.eventBus = EventBus.getInstance();

//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.game_main);
        mBackgroundImage = (ImageView) findViewById(R.id.background_image);

        Shared.activity = this;
        Shared.engine.start();
        Shared.engine.setBackgroundImageView(mBackgroundImage);

        // set background
        setBackgroundImage();

        // set menu
        ScreenController.getInstance().openScreen(Screen.CUSTOM, getIntent().getIntExtra(MainActivity.LEVEL, 1));
    }

    @Override
    protected void onDestroy() {
        Shared.engine.stop();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (PopupManager.isShown()) {
            PopupManager.closePopup();
            if (ScreenController.getLastScreen() == Screen.GAME) {
                Shared.eventBus.notify(new BackGameEvent());
            }
        } else if (ScreenController.getInstance().onBack()) {
            super.onBackPressed();
        }
    }

    private void setBackgroundImage() {
        Bitmap bitmap = Utils.scaleDown(R.drawable.background, Utils.screenWidth(), Utils.screenHeight());
        bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
        bitmap = Utils.downscaleBitmap(bitmap, 2);
        mBackgroundImage.setImageBitmap(bitmap);
    }
}
