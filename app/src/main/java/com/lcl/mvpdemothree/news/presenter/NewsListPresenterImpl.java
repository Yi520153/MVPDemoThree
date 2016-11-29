package com.lcl.mvpdemothree.news.presenter;

import com.lcl.mvpdemothree.beans.NewsBean;
import com.lcl.mvpdemothree.utils.Urls;
import com.lcl.mvpdemothree.news.model.NewsModel;
import com.lcl.mvpdemothree.news.model.NewsModelImpl;
import com.lcl.mvpdemothree.news.model.OnLoadNewsListListener;
import com.lcl.mvpdemothree.news.view.NewsFragment;
import com.lcl.mvpdemothree.news.view.NewsListView;

import java.util.List;

/**
 * Description : 新闻列表Presenter
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public class NewsListPresenterImpl implements NewsListPresenter, OnLoadNewsListListener {

    private static final String TAG = NewsListPresenterImpl.class.getSimpleName();

    private NewsListView mNewsListView;
    private NewsModel mNewsModel;


    public NewsListPresenterImpl(NewsListView mNewsListView) {
        this.mNewsListView = mNewsListView;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(int type, int page) {
        String url = getUrl(type, page);
        if (page == 0) {
            mNewsListView.showProgress();
        }
        mNewsModel.loadNews(url, type, this);
    }

    /**
     * 根据类别和页面引索创建URL
     *
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEW_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        mNewsListView.hideProgress();
        mNewsListView.addNews(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsListView.hideProgress();
        mNewsListView.showLoadFailMsg();
    }

}
