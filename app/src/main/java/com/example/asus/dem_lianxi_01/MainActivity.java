package com.example.asus.dem_lianxi_01;

import android.app.Activity;
import android.support.v4.app.Fragment;
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
import Adapter.NewsAdapter;
import Bean.Bean;
import Fragment.Fragment_left;
import Fragment.Fragment_right;
import Fragment.Fragment_top;
import Fragment.Fragment_yule;
import Fragment.Fragment_shehui;
import Fragment.Fragment_tiyu;
import Fragment.Fragment_keji;
import Fragment.Fragment_caijing;
import Fragment.Fragment_shishang;
import Fragment.Fragment_junshi;
import Utils.Api;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import com.kson.slidingmenu.SlidingMenu;
import com.kson.slidingmenu.app.SlidingFragmentActivity;
import Bean.CategoryBean;
import view.HorizontalScollTabhost;
import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends SlidingFragmentActivity implements View.OnClickListener {
  //  @ViewInject(R.id.xlv_list)
   // XListView xlv_list;
    @ViewInject(R.id.img_icon)
    ImageView img_icon;
    @ViewInject(R.id.img_more)
    ImageView img_more;
    private SlidingMenu menu;
    private HorizontalScollTabhost tabhost;
    private List<CategoryBean> beans;
    private List<Fragment> fragmentlist;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ff6600"));
        //setContentView(R.layout.activity_main);
        x.view().inject(this);
       initdata();
        initMenu();
        initHorizontal();

    }

    private void initHorizontal() {
        CategoryBean be=new CategoryBean();
        be.id="top";
        be.name="头条";
        beans.add(be);
        be = new CategoryBean();
        be.name = "娱乐";
        be.id = "yule";
        beans.add(be);
        be = new CategoryBean();
        be.name = "社会";
        be.id = "shehui";
        beans.add(be);
        be = new CategoryBean();
        be.name = "体育";
        be.id = "tiyu";
        beans.add(be);
        be = new CategoryBean();
        be.name = "科技";
        be.id = "keji";
        beans.add(be);
        be = new CategoryBean();
        be.name = "财经";
        be.id = "caijing";
        beans.add(be);
        be = new CategoryBean();
        be.name = "时尚";
        be.id = "shishang";
        beans.add(be);
        be = new CategoryBean();
        be.name = "军事";
        be.id = "junshi";
        beans.add(be);
        fragmentlist.add(new Fragment_top());
        fragmentlist.add(new Fragment_yule());
        fragmentlist.add(new Fragment_shehui());
        fragmentlist.add(new Fragment_tiyu());
        fragmentlist.add(new Fragment_keji());
        fragmentlist.add(new Fragment_caijing());
        fragmentlist.add(new Fragment_shishang());
        fragmentlist.add(new Fragment_junshi());
        tabhost.diaplay(beans,fragmentlist);
    }
    /**
     * 添加左菜单右菜单
     */
    private void initMenu() {
        //添加左菜单
        setBehindContentView(R.layout.left_fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_left,new Fragment_left()).commit();
        //设置slidingmenu相关属性
        menu=getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.BehindOffsetRes);
        //设置右菜单
        menu.setSecondaryMenu(R.layout.right_fragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_right,new Fragment_right()).commit();
    }
    private void initdata() {
        tabhost= (HorizontalScollTabhost) findViewById(R.id.tabhost);
        beans= new ArrayList<>();
        fragmentlist=new ArrayList<>();
        img_icon.setOnClickListener(this);
        img_more.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_icon:
                menu.showMenu();
                break;
            case R.id.img_more:
                menu.showSecondaryMenu();
                break;
        }
    }
}
