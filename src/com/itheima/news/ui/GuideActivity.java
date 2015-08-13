package com.itheima.news.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.itheima.news.R;
import com.itheima.news.utils.IntentUtils;
import com.itheima.news.utils.SharedPreferenceUtils;

public class GuideActivity extends Activity {

	private ViewPager wlecome_vp;
	private LinearLayout welcome_ll_pointContainer;
	private ImageView guide_point_selection;
	private Button guide_btn;

	private int[] imgs = { R.drawable.guide_1, R.drawable.guide_2,
			R.drawable.guide_3 };
	private List<ImageView> views;
	private int offset; // 两点间距离

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		wlecome_vp = (ViewPager) findViewById(R.id.wlecome_vp);
		welcome_ll_pointContainer = (LinearLayout) findViewById(R.id.welcome_ll_point);
		guide_point_selection = (ImageView) findViewById(R.id.guide_point_selection);
		guide_btn = (Button) findViewById(R.id.guide_btn);

		guide_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IntentUtils.startActivityAndFinish(GuideActivity.this,
						HomeActivity.class);
				SharedPreferenceUtils.setBoolean(getApplicationContext(),
						WelcomeActivity.ISFIRSTTIME, true);
			}
		});

		views = new ArrayList<ImageView>();
		for (int i = 0; i < imgs.length; i++) {

			ImageView iv = new ImageView(getApplicationContext());
			iv.setImageResource(imgs[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			views.add(iv);

			ImageView point = new ImageView(this);
			point.setImageResource(R.drawable.point_normal);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					10, 10);
			if (i != 0) {
				params.leftMargin = 10;
			}
			welcome_ll_pointContainer.addView(point, params);
		}

		// getViewTreeObserver获取全局的控件观察者,然后添加全局布局监听器
		guide_point_selection.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						offset = welcome_ll_pointContainer.getChildAt(1)
								.getLeft()
								- welcome_ll_pointContainer.getChildAt(0)
										.getLeft();
					}
				});

		wlecome_vp.setAdapter(new GuideAdapter());

		wlecome_vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (position == views.size() - 1) {
					// 最后一个页面
					guide_btn.setVisibility(View.VISIBLE);
				}else{
					guide_btn.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

				float scrolllen = offset * arg1;
				// 获取需要设置点的布局变量,通过设置marginleft的值来保证偏移量,注意控件本身是没有LayoutParams,所以得到的是其实通过父容器获取到的params
				RelativeLayout.LayoutParams params = (LayoutParams) guide_point_selection
						.getLayoutParams();
				params.leftMargin = (int) (scrolllen + offset * arg0 + 0.5f);
				guide_point_selection.setLayoutParams(params);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	private class GuideAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			if (views != null) {
				return views.size();
			}
			return 0;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(views.get(position));
			return views.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}
	}

}
