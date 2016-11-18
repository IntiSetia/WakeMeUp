package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.AbstractEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.EventObserver;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.themes.Theme;

public class ThemeSelectedEvent extends AbstractEvent {

	public static final String TYPE = ThemeSelectedEvent.class.getName();
	public final Theme theme;

	public ThemeSelectedEvent(Theme theme) {
		this.theme = theme;
	}

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
