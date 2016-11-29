package com.lcl.mvpdemothree.main.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lcl.mvpdemothree.R;
import com.lcl.mvpdemothree.main.presenter.MainPresenterImpl;
import com.lcl.mvpdemothree.news.view.NewsFragment;

/**
 * Description : 主界面Presenter
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */
public class MainActivity extends AppCompatActivity implements IMainView {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private MainPresenterImpl mMainPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

        mMainPresenterImpl=new MainPresenterImpl(this);

        switchNews();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mMainPresenterImpl.switchNavigation(item.getItemId());
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void switchNews() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new NewsFragment()).commit();
        mToolbar.setTitle(R.string.navigation_news);
    }

    @Override
    public void switchImages() {
        Snackbar.make(mDrawerLayout,getString(R.string.navigation_images_view),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void switchWeather() {
        Snackbar.make(mDrawerLayout,getString(R.string.navigation_weather_view),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void switchAbout() {
        Snackbar.make(mDrawerLayout,getString(R.string.navigation_about_view),Snackbar.LENGTH_SHORT).show();
    }
}
