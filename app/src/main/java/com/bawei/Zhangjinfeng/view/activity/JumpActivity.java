package com.bawei.Zhangjinfeng.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.bawei.Zhangjinfeng.R;

import java.util.Timer;
import java.util.TimerTask;

public class JumpActivity extends AppCompatActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent i = new Intent(JumpActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);

            }
        }, 3000);
    }
}
