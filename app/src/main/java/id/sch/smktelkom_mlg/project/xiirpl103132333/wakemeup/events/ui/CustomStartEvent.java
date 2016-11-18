package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.ui;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.AbstractEvent;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.EventObserver;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.themes.Theme;

/**
 * When the 'back to menu' was pressed.
 */
public class CustomStartEvent extends AbstractEvent {

    public static final String TYPE = DifficultySelectedEvent.class.getName();

    public final int difficulty;
    public final Theme theme;

    public CustomStartEvent(int difficulty, Theme theme) {
        this.difficulty = difficulty;
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
