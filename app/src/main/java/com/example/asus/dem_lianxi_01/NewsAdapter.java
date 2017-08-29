package com.example.asus.dem_lianxi_01;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by asus on 2017/8/29.
 */

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<Bean> list;
    private final int atype=0;
    private final int btype=1;
    private final int type_num=2;

    public NewsAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return type_num;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return atype;
        }else{
            return btype;
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type=getItemViewType(i);
        if(view==null){
            switch (type){
                case atype:
                    view=View.inflate(context,R.layout.a_item,null);
                    break;
                case btype:
                    view=View.inflate(context,R.layout.b_item,null);
                    break;
            }
        }
        switch (type){
            case atype:
                TextView title=view.findViewById(R.id.title);
                title.setText(list.get(i).title);
                ImageView img=view.findViewById(R.id.img);
                ImageLoader.getInstance().displayImage(list.get(i).thumbnail_pic_s,img);
                TextView zuozhe=view.findViewById(R.id.zuozhe);
                zuozhe.setText(list.get(i).author_name);
                TextView date=view.findViewById(R.id.date);
                date.setText(list.get(i).date);
                break;
            case btype:
                TextView title1=view.findViewById(R.id.title1);
                title1.setText(list.get(i).title);
                ImageView img1=view.findViewById(R.id.img1);
                ImageLoader.getInstance().displayImage(list.get(i).thumbnail_pic_s,img1);
                TextView zuozhe1=view.findViewById(R.id.zuozhe1);
                zuozhe1.setText(list.get(i).author_name);
                TextView date1=view.findViewById(R.id.date1);
                date1.setText(list.get(i).date);
                break;
        }
        return view;
    }
}
