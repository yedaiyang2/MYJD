package com.bawei.Zhangjinfeng.view.Iview;

import com.bawei.Zhangjinfeng.model.bean.LoginBean;


public interface LoginActivityInter {

    void getLoginSuccess(LoginBean loginBean);


    void getLoginSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);
}
