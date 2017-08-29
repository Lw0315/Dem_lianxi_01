package com.example.asus.dem_lianxi_01;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.xutils.x;

/**
 * Created by asus on 2017/8/29.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initload();
    }

    private void initload() {
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(30))
                .build();
        ImageLoaderConfiguration con=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(con);
    }

    private void init() {
        x.Ext.init(this);
    }
}
