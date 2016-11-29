package com.lcl.mvpdemothree.news.model;

import com.lcl.mvpdemothree.beans.NewsDetailBean;

/**
 * Description : 新闻详情数据加载监听
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public interface OnLoadNewsDetailListener {
    void onSuccess(NewsDetailBean newsDetailBean);

    void onFailure(String msg, Exception e);
}
