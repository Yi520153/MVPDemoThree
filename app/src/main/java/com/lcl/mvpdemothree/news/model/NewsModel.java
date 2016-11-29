package com.lcl.mvpdemothree.news.model;

/**
 * Description : 新闻数据处理类接口
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public interface NewsModel {
    /**
     *  加载新闻列表数据
     * @param url
     * @param type
     * @param onLoadNewsListListener
     */
    void loadNews(String url, int type, OnLoadNewsListListener onLoadNewsListListener);

    /**
     * 加载新闻详情数据
     * @param docId
     * @param onLoadNewsDetailListener
     */
    void loadNewsDetail(String docId, OnLoadNewsDetailListener onLoadNewsDetailListener);
}
