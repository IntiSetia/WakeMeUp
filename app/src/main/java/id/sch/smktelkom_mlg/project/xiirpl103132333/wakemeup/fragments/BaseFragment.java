package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.fragments;

import android.support.v4.app.Fragment;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.EventObserver;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.engine.FlipDownCardsEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.engine.GameWonEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.engine.HidePairCardsEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.BackGameEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.CustomStartEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.DifficultySelectedEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.FlipCardEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.NextGameEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.ResetBackgroundEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.StartEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui.ThemeSelectedEvent;

public class BaseFragment extends Fragment implements EventObserver {

	@Override
	public void onEvent(FlipCardEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(DifficultySelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(CustomStartEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(HidePairCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(FlipDownCardsEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(StartEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ThemeSelectedEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(GameWonEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(BackGameEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(NextGameEvent event) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onEvent(ResetBackgroundEvent event) {
		throw new UnsupportedOperationException();
	}

}
