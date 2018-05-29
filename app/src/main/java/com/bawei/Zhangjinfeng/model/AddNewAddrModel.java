package com.bawei.Zhangjinfeng.model;

import com.bawei.Zhangjinfeng.model.bean.AddNewAddrBean;
import com.bawei.Zhangjinfeng.presenter.inter.AddNewAddrPresenterInter;
import com.bawei.Zhangjinfeng.util.CommonUtils;
import com.bawei.Zhangjinfeng.util.OkHttp3Util_03;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class AddNewAddrModel {


    private AddNewAddrPresenterInter addNewAddrPresenterInter;

    public AddNewAddrModel(AddNewAddrPresenterInter addNewAddrPresenterInter) {
        this.addNewAddrPresenterInter = addNewAddrPresenterInter;
    }

    public void addNewAddr(String addNewAddrUrl, String uid, String addr, String phone, String name) {

        Map<String, String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("addr",addr);
        params.put("mobile",phone);
        params.put("name",name);


        OkHttp3Util_03.doPost(addNewAddrUrl, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    String json = response.body().string();
                    final AddNewAddrBean addNewAddrBean = new Gson().fromJson(json,AddNewAddrBean.class);

                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            addNewAddrPresenterInter.onAddAddrSuccess(addNewAddrBean);
                        }
                    });

                }
            }
        });
    }
}
