package com.itheima.news.utils;

import android.app.Activity;
import android.content.Intent;

public class IntentUtils {

	public static void startActivityAndFinish(
			Activity context,
			Class<? extends Activity> cls) {
		Intent intent = new Intent(context, cls);
		context.startActivity(intent);
		context.finish();
	}
}
