package com.itheima.news.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferenceUtils {
	private static SharedPreferences sp;

	/**
	 * SharedPreferences中获取boolean值
	 * 
	 * @param context
	 * @param key
	 * @param def
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean def) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		}
		boolean res = sp.getBoolean(key, def);
		return res;
	}

	/**
	 * SharedPreferences存取数值
	 * 
	 * @param context
	 * @param key
	 *            ,存取的属性名
	 * @param value属性名对应的值
	 */
	public static void setBoolean(Context context, String key, boolean value) {
		if (sp == null) {
			sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		}
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}
}
