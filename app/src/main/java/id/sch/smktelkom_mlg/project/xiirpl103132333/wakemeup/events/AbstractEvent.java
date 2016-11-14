package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events;

public abstract class AbstractEvent implements Event {

	protected abstract void fire(EventObserver eventObserver);

}
