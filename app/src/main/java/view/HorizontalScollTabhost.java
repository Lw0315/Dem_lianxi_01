package view;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.asus.dem_lianxi_01.R;
import java.util.ArrayList;
import java.util.List;
import Bean.CategoryBean;

/**
 * Created by asus on 2017/8/30.
 */

public class HorizontalScollTabhost extends LinearLayout implements ViewPager.OnPageChangeListener {
    private Context context;
    private int mBgColor;
    private List<CategoryBean> list;
    private List<Fragment> fragment_list;
    private List<TextView> topviews;
    private int count;
    private ViewPager vp;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout layout_menu;
    private MyPager mp;
    public HorizontalScollTabhost(Context context) {
        //super(context);
        this(context,null);
    }

    public HorizontalScollTabhost(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizontalScollTabhost(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init(context,attrs);
    }

    /**
     * 初始化自定义属性和view
     * @param context
     * @param attrs
     */
    private void init(Context context,AttributeSet attrs){
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.HorizontalScollTabhost);
        mBgColor=typedArray.getColor(R.styleable.HorizontalScollTabhost_top_background,0xffffff);
        typedArray.recycle();
        initview();
    }

    private void initview() {
        View view= LayoutInflater.from(context).inflate(R.layout.horizontal_scroll_tabhost,this,true);
        horizontalScrollView=view.findViewById(R.id.horizontalScrollView);
        vp=view.findViewById(R.id.vp);
        layout_menu=view.findViewById(R.id.layout_menu);
        vp.addOnPageChangeListener(this);
    }

    /**
     * 供调用者调用，保证数据独立
     */
    public void diaplay(List<CategoryBean> list,List<Fragment> fragments){
        this.list=list;
        this.count=list.size();
        this.fragment_list=fragments;
        topviews=new ArrayList<>(count);
        drawUi();
    }

    /**
     * 绘制页面所有元素
     */
    private void drawUi() {
        drawHorizontal();
        drawViewpager();
    }

    /**
     * 绘制viewpager
     */
    private void drawViewpager() {
        mp=new MyPager(((FragmentActivity)context).getSupportFragmentManager());
        vp.setAdapter(mp);
    }

    /**
     * 绘制横向滑动菜单
     */
    private void drawHorizontal() {
         layout_menu.setBackgroundColor(mBgColor);
        for (int i = 0; i <count ; i++) {
            CategoryBean bean=list.get(i);
            final TextView tv= (TextView) View.inflate(context,R.layout.news_top_tv_item,null);
            tv.setText(bean.name);
            final int finalI=i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //点击移动到当前的fragment
                    vp.setCurrentItem(finalI);
                    //点击让文字居中
                    moveItemToCenter(tv);
                }
            });
            layout_menu.addView(tv);
            topviews.add(tv);
        }
        //默认设置第一项为选中
        topviews.get(0).setSelected(true);
    }
    private void moveItemToCenter(TextView tv){
        DisplayMetrics dm=getResources().getDisplayMetrics();
        int screenWidth=dm.widthPixels;
        int[] locations=new int[2];
        tv.getLocationInWindow(locations);
        int rbWidth=tv.getWidth();
        horizontalScrollView.smoothScrollBy((locations[0]+rbWidth/2-screenWidth/2),0);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(layout_menu!=null&&layout_menu.getChildCount()>0){
            for (int i = 0; i <layout_menu.getChildCount() ; i++) {
                if(i==position){
                    layout_menu.getChildAt(i).setSelected(true);
                }else{
                    layout_menu.getChildAt(i).setSelected(false);
                }
            }
        }
        //移动view,水平居中
        moveItemToCenter(topviews.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    class MyPager extends FragmentPagerAdapter{

        public MyPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragment_list.get(position);
        }

        @Override
        public int getCount() {
            return fragment_list.size();
        }
    }
}
