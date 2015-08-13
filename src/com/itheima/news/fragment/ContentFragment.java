package com.itheima.news.fragment;

import com.itheima.news.R;

import android.view.View;

public class ContentFragment extends BaseFragment {

	@Override
	protected View initView() {

		View view = View.inflate(mActivity, R.layout.content, null);
		System.out.println(view.getId());
		return view;

	}

}
