package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.engine.Engine;
import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.events.EventBus;

public class Shared {

	public static Context context;
	public static FragmentActivity activity; // it's fine for this app, but better move to weak reference
	public static Engine engine;
	public static EventBus eventBus;

}
