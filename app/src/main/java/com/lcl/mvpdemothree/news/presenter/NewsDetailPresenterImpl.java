package com.lcl.mvpdemothree.news.presenter;

import com.lcl.mvpdemothree.beans.NewsDetailBean;
import com.lcl.mvpdemothree.news.model.NewsModel;
import com.lcl.mvpdemothree.news.model.NewsModelImpl;
import com.lcl.mvpdemothree.news.model.OnLoadNewsDetailListener;
import com.lcl.mvpdemothree.news.view.NewsDetailView;

/**
 * Description : 新闻详情Presenter
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public class NewsDetailPresenterImpl implements NewsDetailPresenter, OnLoadNewsDetailListener {

    private NewsDetailView mNewsDetailView;
    private NewsModel mNewsModel;

    public NewsDetailPresenterImpl(NewsDetailView mNewsDetailView) {
        this.mNewsDetailView = mNewsDetailView;
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(String docId) {
        mNewsDetailView.showProgress();
        mNewsModel.loadNewsDetail(docId, this);
    }

    @Override
    public void onSuccess(NewsDetailBean newsDetailBean) {
        if (newsDetailBean != null) {
            mNewsDetailView.showNewsDetailContent(newsDetailBean.getBody());
        }
        mNewsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetailView.hideProgress();
    }
}
