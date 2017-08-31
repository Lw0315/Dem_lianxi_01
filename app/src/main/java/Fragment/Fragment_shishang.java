package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.dem_lianxi_01.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import Adapter.NewsAdapter;
import Bean.Bean;
import Utils.Api;
import view.xlistview.XListView;

/**
 * Created by asus on 2017/8/30.
 */

public class Fragment_shishang extends Fragment implements XListView.IXListViewListener {

    private XListView xlv_list;
    private List<Bean> list;
    private NewsAdapter na;
    private String type="shishang";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.top_fragment,null);
        //x.view().inject(getActivity());
        xlv_list=view.findViewById(R.id.xlv_list);
        initdata();
        getnews();
        return view;
    }

    public void getnews() {
        RequestParams params=new RequestParams(Api.Url);
        params.addParameter("key",Api.key);
        params.addParameter("type",type);

        x.http().post(params, new Callback.CommonCallback<String>() {
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
                na=new NewsAdapter(getActivity(),list);
                xlv_list.setAdapter(na);
                xlv_list.stopRefresh();
                xlv_list.stopLoadMore();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initdata() {
        list=new ArrayList<>();
        xlv_list.setPullLoadEnable(true);
        xlv_list.setXListViewListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
