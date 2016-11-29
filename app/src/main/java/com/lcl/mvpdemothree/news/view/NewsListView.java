package com.lcl.mvpdemothree.news.view;

import com.lcl.mvpdemothree.beans.NewsBean;

import java.util.List;

/**
 * Description : 新闻列表View接口
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public interface NewsListView {
    void showProgress();

    void addNews(List<NewsBean> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
