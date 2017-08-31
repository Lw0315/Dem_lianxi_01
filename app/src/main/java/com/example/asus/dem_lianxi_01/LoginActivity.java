package com.example.asus.dem_lianxi_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
@ContentView(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(R.id.cha)
    ImageView cha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        x.view().inject(this);
        initview();
    }

    private void initview() {
        cha.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
