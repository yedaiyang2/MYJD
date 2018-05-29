package com.bawei.Zhangjinfeng.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.Zhangjinfeng.R;
import com.bawei.Zhangjinfeng.view.fragment.FragmentProvince;

public class ChooseDistinctActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_distinct);

        //已进入这个activity显示的是省的数据...用省的fragment进行替换
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new FragmentProvince()).commit();
    }
}
