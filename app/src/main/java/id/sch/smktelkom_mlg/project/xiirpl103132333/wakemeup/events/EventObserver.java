package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events;

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


public interface EventObserver {

	void onEvent(FlipCardEvent event);

	void onEvent(DifficultySelectedEvent event);

	void onEvent(CustomStartEvent event);

	void onEvent(HidePairCardsEvent event);

	void onEvent(FlipDownCardsEvent event);

	void onEvent(StartEvent event);

	void onEvent(ThemeSelectedEvent event);

	void onEvent(GameWonEvent event);

	void onEvent(BackGameEvent event);

	void onEvent(NextGameEvent event);

	void onEvent(ResetBackgroundEvent event);

}
