package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.common;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.R;

public class Music {

	public static boolean OFF = false;

	public static void playCorrent() {
		if (!OFF) {
			MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.correct_answer);
			mp.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.reset();
					mp.release();
					mp = null;
				}

			});
			mp.start();
		}
	}

	public static void playBackgroundMusic() {
		// TODO
	}

	public static void showStar() {
		if (!OFF) {
			MediaPlayer mp = MediaPlayer.create(Shared.context, R.raw.star);
			mp.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.reset();
					mp.release();
					mp = null;
				}

			});
			mp.start();
		}
	}
}
