package com.lcl.mvpdemothree.news.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcl.mvpdemothree.R;
import com.lcl.mvpdemothree.beans.NewsBean;
import com.lcl.mvpdemothree.utils.Urls;
import com.lcl.mvpdemothree.news.adapter.NewsListAdapter;
import com.lcl.mvpdemothree.news.presenter.NewsListPresenter;
import com.lcl.mvpdemothree.news.presenter.NewsListPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : 新闻列表
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public class NewsListFragment extends Fragment implements NewsListView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = NewsListFragment.class.getSimpleName();

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NewsListAdapter mAdapter;
    private List<NewsBean> mData;
    private NewsListPresenter mNewsListPresenter;

    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;

    private boolean isLoading=false;


    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsListPresenter = new NewsListPresenterImpl(this);
        mType = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark,
                R.color.primary_light, R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsListAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                //加载更多
                mNewsListPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
            }
        }
    };

    private NewsListAdapter.OnItemClickListener mOnItemClickListener = new NewsListAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {
            if (mData.size() <= 0) {
                return;
            }
            NewsBean news = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news",news);

            View transitionView = view.findViewById(R.id.ivNews);
            ActivityOptionsCompat optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            transitionView,getString(R.string.transition_news_img));
            ActivityCompat.startActivity(getActivity(),intent,optionsCompat.toBundle());
        }
    };

    @Override
    public void onRefresh() {
        if(!isLoading){
            isLoading=true;
            pageIndex=0;
            mNewsListPresenter.loadNews(mType,pageIndex);
        }
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void addNews(List<NewsBean> newsList) {
        mAdapter.setShowFooter(true);
        if(mData==null){
            mData=new ArrayList<>();
        }else if(pageIndex==0){
            mData.clear();
        }
        mData.addAll(newsList);
        if(pageIndex==0){
            mAdapter.setMData(mData);
        }else{
            if(newsList==null||newsList.size()==0){
                mAdapter.setShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex+=Urls.PAZE_SIZE;
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
        isLoading=false;
    }

    @Override
    public void showLoadFailMsg() {
        if(pageIndex==0){
            mAdapter.setShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
        View view=getActivity()==null?mRecyclerView.getRootView():getActivity().findViewById(R.id.drawer_layout);
        Snackbar.make(view,getString(R.string.load_fail),Snackbar.LENGTH_SHORT).show();
    }

}
