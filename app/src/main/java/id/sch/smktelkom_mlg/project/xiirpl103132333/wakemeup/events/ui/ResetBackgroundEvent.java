package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.AbstractEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class ResetBackgroundEvent extends AbstractEvent {

	public static final String TYPE = ResetBackgroundEvent.class.getName();

	@Override
	protected void fire(EventObserver eventObserver) {
		eventObserver.onEvent(this);
	}

	@Override
	public String getType() {
		return TYPE;
	}

}
