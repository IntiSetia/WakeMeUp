package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.engine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common.Shared;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.ResetBackgroundEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.fragments.CustomFragment;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.fragments.DifficultySelectFragment;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.fragments.GameFragment;

public class ScreenController {

	private static ScreenController mInstance = null;
	private static List<Screen> openedScreens = new ArrayList<Screen>();
	private FragmentManager mFragmentManager;
	private int level;

	private ScreenController() {
	}

	public static ScreenController getInstance() {
		if (mInstance == null) {
			mInstance = new ScreenController();
		}
		return mInstance;
	}

	public static Screen getLastScreen() {
		return openedScreens.get(openedScreens.size() - 1);
	}

	public void openScreen(Screen screen, int level) {
		mFragmentManager = Shared.activity.getSupportFragmentManager();
		this.level = level;
		if (screen == Screen.GAME && openedScreens.get(openedScreens.size() - 1) == Screen.GAME) {
			openedScreens.remove(openedScreens.size() - 1);
		} else if (screen == Screen.DIFFICULTY && openedScreens.get(openedScreens.size() - 1) == Screen.GAME) {
			openedScreens.remove(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
		}
		Fragment fragment = getFragment(screen);
		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment);
		fragmentTransaction.commit();
		openedScreens.add(screen);
	}

	public void openScreen(Screen screen) {
		openScreen(screen, 0);
	}

	public boolean onBack() {
		if (openedScreens.size() > 0) {
			Screen screenToRemove = openedScreens.get(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
			if (openedScreens.size() == 0) {
				return true;
			}
			Screen screen = openedScreens.get(openedScreens.size() - 1);
			openedScreens.remove(openedScreens.size() - 1);
			openScreen(screen);
			if ((screen == Screen.THEME_SELECT || screen == Screen.MENU) &&
					(screenToRemove == Screen.DIFFICULTY || screenToRemove == Screen.GAME)) {
				Shared.eventBus.notify(new ResetBackgroundEvent());
			}
			return false;
		}
		return true;
	}

	private Fragment getFragment(Screen screen) {
		switch (screen) {
		case CUSTOM:
			return new CustomFragment().newInstance(level);
		case DIFFICULTY:
			return new DifficultySelectFragment();
		case GAME:
			return new GameFragment();
		default:
			break;
		}
		return null;
	}

	public enum Screen {
		MENU,
		CUSTOM,
		GAME,
		DIFFICULTY,
		THEME_SELECT
	}
}
