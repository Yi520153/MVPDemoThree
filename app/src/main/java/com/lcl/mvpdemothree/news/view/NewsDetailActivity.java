package com.lcl.mvpdemothree.news.view;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lcl.mvpdemothree.R;
import com.lcl.mvpdemothree.beans.NewsBean;
import com.lcl.mvpdemothree.news.presenter.NewsDetailPresenter;
import com.lcl.mvpdemothree.news.presenter.NewsDetailPresenterImpl;
import com.lcl.mvpdemothree.utils.HtmlWebView;
import com.lcl.mvpdemothree.utils.ImageLoaderUtils;

/**
 * Description : 新闻详情
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */
public class NewsDetailActivity extends AppCompatActivity implements NewsDetailView{

    private NewsBean mNews;
    private HtmlWebView mTVNewsContent;
    private NewsDetailPresenter mNewsDetailPresenter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        mProgressBar= (ProgressBar) findViewById(R.id.progress);
        mTVNewsContent= (HtmlWebView) findViewById(R.id.htNewsContent);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mNews = (NewsBean) getIntent().getSerializableExtra("news");

        CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(mNews.getTitle());

        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.ivImage),mNews.getImgsrc());

        mNewsDetailPresenter=new NewsDetailPresenterImpl(this);
        mNewsDetailPresenter.loadNewsDetail(mNews.getDocid());
    }

    @Override
    public void showNewsDetailContent(String newsDetailContent) {
        mTVNewsContent.loadHtmlData(newsDetailContent);
        //mTVNewsContent.setHtmlFromString(newsDetailContent,new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
