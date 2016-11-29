package com.lcl.mvpdemothree.news.view;

/**
 * Description : 新闻详情View接口
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public interface NewsDetailView {

    void showNewsDetailContent(String newsDetailContent);

    void showProgress();

    void hideProgress();
}
