package com.lcl.mvpdemothree.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcl.mvpdemothree.R;
import com.lcl.mvpdemothree.news.adapter.NewsFragmentPagerAdapter;

/**
 * Description : 新闻主页面
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public class NewsFragment extends Fragment{

    public static final int NEWS_TYPE_TOP=0;
    public static final int NEWS_TYPE_NBA=1;
    public static final int NEWS_TYPE_CARS=2;
    public static final int NEW_TYPE_JOKES=3;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_news,null);
        mTabLayout= (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager= (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager(mViewPager);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.top));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.nba));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.cars));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.jokes));
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private void setupViewPager(ViewPager mViewPager){
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManger(),否则会有问题
        NewsFragmentPagerAdapter adapter=new NewsFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP),getString(R.string.top));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA),getString(R.string.nba));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS),getString(R.string.cars));
        adapter.addFragment(NewsListFragment.newInstance(NEW_TYPE_JOKES),getString(R.string.jokes));
        mViewPager.setAdapter(adapter);
    }
}
