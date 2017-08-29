package com.example.asus.dem_lianxi_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {
     @ViewInject(R.id.xlv_list)
    XListView xlv_list;
    private List<Bean> list;
    private NewsAdapter na;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);
        initdata();
        getnews();
    }

    private void initdata() {
        list=new ArrayList<>();
        xlv_list.setPullLoadEnable(true);
        xlv_list.setXListViewListener(this);
    }

    public void getnews() {
        RequestParams params=new RequestParams(Api.Url+Api.key);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("==="+result.toString());
                pardata(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 解析
     * @param result
     */
    private void pardata(String result) {
        try{
            JSONObject obj=new JSONObject(result);
            JSONObject resultobj=obj.getJSONObject("result");
            JSONArray arraydata=resultobj.getJSONArray("data");
            if(arraydata!=null&&arraydata.length()>0){
                for (int i = 0; i < arraydata.length(); i++) {
                    JSONObject data= (JSONObject) arraydata.get(i);
                    Bean bean=new Bean();
                    bean.title=data.getString("title");
                    bean.date=data.getString("date");
                    bean.author_name=data.getString("author_name");
                    bean.thumbnail_pic_s=data.getString("thumbnail_pic_s");
                    list.add(bean);
                }
            }
            if(list!=null){
                na=new NewsAdapter(MainActivity.this,list);
                xlv_list.setAdapter(na);
                xlv_list.stopRefresh();
                xlv_list.stopLoadMore();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
