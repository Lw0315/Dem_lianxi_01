package Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.dem_lianxi_01.LoginActivity;
import com.example.asus.dem_lianxi_01.R;

import org.xutils.view.annotation.ContentView;

/**
 * Created by asus on 2017/8/30.
 */

public class Fragment_left extends Fragment implements View.OnClickListener {

    private TextView login;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.left_item,null);
        login=view.findViewById(R.id.login);
        login.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
