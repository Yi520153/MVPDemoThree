package com.lcl.mvpdemothree.main.presenter;

import com.lcl.mvpdemothree.R;
import com.lcl.mvpdemothree.main.view.IMainView;

/**
 * Description : 主界面Presenter
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */
public class MainPresenterImpl implements IMainPresenter{

    private IMainView mMainView;

    public MainPresenterImpl(IMainView mMainView){
        this.mMainView=mMainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id){
            case R.id.navigation_item_news:
                mMainView.switchNews();
                break;
            case R.id.navigation_item_images:
                mMainView.switchImages();
                break;
            case R.id.navigation_item_weather:
                mMainView.switchWeather();
                break;
            case R.id.navigation_item_about:
                mMainView.switchAbout();
                break;
            default:
                mMainView.switchNews();
                break;
        }
    }
}
