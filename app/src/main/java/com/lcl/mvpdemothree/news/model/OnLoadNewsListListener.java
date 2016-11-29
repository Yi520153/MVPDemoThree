package com.lcl.mvpdemothree.news.model;

import com.lcl.mvpdemothree.beans.NewsBean;

import java.util.List;

/**
 * Description : 新闻列表数据加载监听
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public interface OnLoadNewsListListener {

    void onSuccess(List<NewsBean> list);

    void onFailure(String msg, Exception e);
}
