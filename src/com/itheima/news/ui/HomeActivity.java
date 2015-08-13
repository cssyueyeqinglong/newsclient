package com.itheima.news.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.itheima.news.R;
import com.itheima.news.fragment.ContentFragment;
import com.itheima.news.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setBehindContentView(R.layout.menu_activity);
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setBehindWidth(120);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// 分流
		FragmentManager fm = getSupportFragmentManager();
		// 开启事物
		FragmentTransaction transaction = fm.beginTransaction();

		transaction.replace(R.id.home_content, new ContentFragment());
		transaction.replace(R.id.home_menu, new MenuFragment());

		transaction.commit();
	}
}
