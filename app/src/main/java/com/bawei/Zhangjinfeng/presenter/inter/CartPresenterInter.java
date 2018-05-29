package com.bawei.Zhangjinfeng.presenter.inter;

import com.bawei.Zhangjinfeng.model.bean.CartBean;


public interface CartPresenterInter {
    void getCartDataNull();

    void getCartDataSuccess(CartBean cartBean);
}
