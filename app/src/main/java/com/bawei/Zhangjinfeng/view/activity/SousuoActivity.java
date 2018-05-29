package com.bawei.Zhangjinfeng.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.Zhangjinfeng.R;
import com.bawei.Zhangjinfeng.model.bean.User;
import com.bawei.Zhangjinfeng.view.adapter.MyEditAdapter;

import java.util.ArrayList;
import java.util.List;

public class SousuoActivity extends AppCompatActivity {

    private TextView back;
    private TextView sousuo;
    private EditText text;
    private ListView lv;
    private List<User> list = new ArrayList<>();
    private MyEditAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        back = (TextView) findViewById(R.id.main_edit_back);
        sousuo = (TextView) findViewById(R.id.main_edit_sousuo);
        text = (EditText) findViewById(R.id.main_edit_text);
        lv = (ListView) findViewById(R.id.lv);
        //点击结束当前
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = text.getText().toString();
                if (s.trim() == null || s.trim().equals("")) {
                    Toast.makeText(SousuoActivity.this, "输入框为空", Toast.LENGTH_LONG).show();
                } else {
                    list.add(new User(s));
                    Intent intent=new Intent(SousuoActivity.this,ProductListActivity.class);
                    intent.putExtra("name",s);
                    startActivity(intent);
                }

                myadapter = new MyEditAdapter(SousuoActivity.this, list);
                lv.setAdapter(myadapter);
                myadapter.notifyDataSetChanged();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(SousuoActivity.this, list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void btn_clear(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SousuoActivity.this);
        builder.setMessage("确认要清空吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (list.size() <= 0) {
                    Toast.makeText(SousuoActivity.this, "没有东西可以删除", Toast.LENGTH_LONG).show();
                } else {
                    list.clear();
                    Toast.makeText(SousuoActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    myadapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();



    }
    }

