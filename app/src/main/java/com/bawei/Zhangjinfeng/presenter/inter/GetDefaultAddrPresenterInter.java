package com.bawei.Zhangjinfeng.presenter.inter;

import com.bawei.Zhangjinfeng.model.bean.DefaultAddrBean;


public interface GetDefaultAddrPresenterInter {
    void onGetDefaultAddrSuccess(DefaultAddrBean defaultAddrBean);

    void onGetDefaultAddrEmpty();
}
