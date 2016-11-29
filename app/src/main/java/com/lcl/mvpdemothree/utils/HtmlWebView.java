package com.lcl.mvpdemothree.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;

/**
 * Description : 显示Html代码webView工具类
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */
public class HtmlWebView extends WebView {

    public HtmlWebView(Context context) {
        super(context);
        initSetting();
    }

    public HtmlWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting();
    }

    public HtmlWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();
    }

    private void initSetting(){
       //支持javascript
        this.getSettings().setJavaScriptEnabled(true);
        setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY); //设置滚动条样式
        setHorizontalScrollBarEnabled(false);//水平不显示
        setVerticalScrollBarEnabled(false); //垂直不显示
    }

    public void loadHtmlData(String data){
        String customHtml = "<html><body>"+
                data.replaceAll("&lt;","<").replaceAll("&quot;","\"").replaceAll("&amp;","&").replaceAll("&gt;",">").replaceAll("&nbsp;"," ")+"</body></html>";
        //Debug.d("HtmlWebView","getNewContent(customHtml)="+getNewContent(customHtml));
        loadDataWithBaseURL(null,customHtml, "text/html", "utf-8", null);
    }

}
