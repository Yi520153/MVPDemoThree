package com.lcl.mvpdemothree.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : 新闻主页面ViewPager适配器
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> mFragments=new ArrayList<>();
    private List<String> mFragmentTitles=new ArrayList<>();

    public NewsFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment,String title){
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public CharSequence getPageTitle(int position){
        return mFragmentTitles.get(position);
    }
}
