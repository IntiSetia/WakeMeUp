package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.AbstractEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.EventObserver;

/**
 * When the 'back to menu' was pressed.
 */
public class FlipCardEvent extends AbstractEvent {

	public static final String TYPE = FlipCardEvent.class.getName();

	public final int id;
	
	public FlipCardEvent(int id) {
		this.id = id;
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
