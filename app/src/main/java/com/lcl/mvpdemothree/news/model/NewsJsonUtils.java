package com.lcl.mvpdemothree.news.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lcl.mvpdemothree.beans.NewsBean;
import com.lcl.mvpdemothree.beans.NewsDetailBean;
import com.lcl.mvpdemothree.utils.JsonUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * Description : Json数据解析工具类
 * Author : lcl
 * Blog   : http://blog.csdn.net/qq_32955807
 */

public class NewsJsonUtils {

    private final static String TAG=NewsJsonUtils.class.getSimpleName();

    /**
     * 解析列表数据
     * @param res
     * @param value
     * @return
     */
    public static List<NewsBean> readJsonNewsBeans(String res,String value){
        List<NewsBean> beens=new ArrayList<>();
        try {
            JsonParser parser=new JsonParser();
            JsonObject jsonObject=parser.parse(res).getAsJsonObject();
            JsonElement jsonElement=jsonObject.get(value);
            if(jsonElement==null){
                return null;
            }

            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for(int i=1;i<jsonArray.size();i++){
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                if(jo.has("skipType")&&"special".equals(jo.get("skipType").getAsString())){
                    continue;
                }
                if(jo.has("TAGS")&&!jo.has("TAG")){
                    continue;
                }
                if(!jo.has("imgextra")){
                    NewsBean news= JsonUtils.deserialize(jo,NewsBean.class);
                    beens.add(news);
                }
            }

        }catch (Exception e){

        }
        return beens;
    }

    /**
     * 解析详情数据
     * @param res
     * @param docId
     * @return
     */
    public static NewsDetailBean readJsonNewsDetailBeans(String res,String docId){
        NewsDetailBean newsDetailBean=null;

        try {
            JsonParser parser=new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement=jsonObj.get(docId);
            if(jsonElement==null){
                return null;
            }
            newsDetailBean=JsonUtils.deserialize(jsonElement.getAsJsonObject(),NewsDetailBean.class);
        }catch (Exception e){

        }
        return newsDetailBean;
    }
}

