package com.bawei.Zhangjinfeng.presenter.inter;

import com.bawei.Zhangjinfeng.model.bean.LoginBean;


public interface LoginPresenterInter {

    void onSuccess(LoginBean loginBean);


    void onSuccessByQQ(LoginBean loginBean, String ni_cheng, String iconurl);
}
