package com.itheima.news.ui;

import android.app.Activity;
import android.graphics.Interpolator;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.itheima.news.R;
import com.itheima.news.utils.IntentUtils;
import com.itheima.news.utils.SharedPreferenceUtils;

public class WelcomeActivity extends Activity {

	public static final String ISFIRSTTIME = "isfirsttime";

	private RelativeLayout splash_iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		splash_iv = (RelativeLayout) findViewById(R.id.splash_bg);

		RotateAnimation animation1 = new RotateAnimation(0, 360,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);

		ScaleAnimation animation2 = new ScaleAnimation(0f, 1.0f, 0f, 1.0f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);

		AnimationSet set = new AnimationSet(false);
		// set.setInterpolator(new CycleInterpolator(7));
		set.addAnimation(animation1);
		set.addAnimation(animation2);
		Interpolator in;
		set.setRepeatMode(RotateAnimation.REVERSE);
		set.setDuration(1000);

		splash_iv.setAnimation(set);
		set.start();

		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				new Thread() {
					public void run() {
						try {
							Thread.sleep(600);
							
							// 判断是否是第一次进入
							boolean res = SharedPreferenceUtils.getBoolean(
									getApplicationContext(), ISFIRSTTIME, false);
							if (res) {
								// 进入主界面
								IntentUtils.startActivityAndFinish(WelcomeActivity.this,
										HomeActivity.class);
							} else {
								// 进入引导页面
								IntentUtils.startActivityAndFinish(WelcomeActivity.this,
										GuideActivity.class);
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					};
				}.start();
				
			}
		});
	}
}
