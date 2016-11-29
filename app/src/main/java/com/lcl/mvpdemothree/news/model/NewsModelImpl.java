package com.lcl.mvpdemothree.news.model;

import com.lcl.mvpdemothree.beans.NewsBean;
import com.lcl.mvpdemothree.beans.NewsDetailBean;
import com.lcl.mvpdemothree.utils.Urls;
import com.lcl.mvpdemothree.news.view.NewsFragment;
import com.lcl.mvpdemothree.utils.OkHttpUtils;

import java.util.List;

/**
 * Description : 新闻数据处理类
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public class NewsModelImpl implements NewsModel {

    /**
     * 加载新闻列表数据
     * @param url
     * @param type
     * @param onLoadNewsListListener
     */
    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener onLoadNewsListListener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback=new OkHttpUtils.ResultCallback<String>(){

            @Override
            public void onSuccess(String response) {
                List<NewsBean> newsBeanList= NewsJsonUtils.readJsonNewsBeans(response,getID(type));
                onLoadNewsListListener.onSuccess(newsBeanList);
            }

            @Override
            public void onFailure(Exception e) {
                onLoadNewsListListener.onFailure("load news list failure.",e);
            }
        };
        OkHttpUtils.get(url,loadNewsCallback);
    }

    @Override
    public void loadNewsDetail(final String docId, final OnLoadNewsDetailListener onLoadNewsDetailListener) {
        String url=getDetailUrl(docId);
        OkHttpUtils.ResultCallback<String> loadNewsCallback=new OkHttpUtils.ResultCallback<String>(){

            @Override
            public void onSuccess(String response) {
                NewsDetailBean newsDetailBean= NewsJsonUtils.readJsonNewsDetailBeans(response,docId);
                onLoadNewsDetailListener.onSuccess(newsDetailBean);
            }

            @Override
            public void onFailure(Exception e) {
                onLoadNewsDetailListener.onFailure("load news detail info failure",e);
            }
        };
        OkHttpUtils.get(url,loadNewsCallback);
    }

    /**
     * 获取ID
     * @param type
     * @return
     */
    private String getID(int type){
        String id="";
        switch (type){
            case NewsFragment.NEWS_TYPE_TOP:
                id= Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id=Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id=Urls.CAR_ID;
                break;
            case NewsFragment.NEW_TYPE_JOKES:
                id=Urls.JOKE_ID;
                break;
            default:
                id=Urls.TOP_ID;
                break;
        }
        return id;
    }

    private String getDetailUrl(String docId){
        StringBuffer sb=new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }
}
