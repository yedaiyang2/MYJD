package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.CartModel;
import com.bawei.Zhangjinfeng.model.bean.CartBean;
import com.bawei.Zhangjinfeng.presenter.inter.CartPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.FragmentCartInter;

/**
 * Created by Dash on 2018/1/30.
 */
public class FragmentCartPresenter implements CartPresenterInter {

    private FragmentCartInter fragmentCartInter;
    private CartModel cartModel;

    public FragmentCartPresenter(FragmentCartInter fragmentCartInter) {
        this.fragmentCartInter = fragmentCartInter;

        cartModel = new CartModel(this);
    }

    public void getCartData(String selectCart, String uid) {

        cartModel.getCartData(selectCart,uid);

    }

    @Override
    public void getCartDataNull() {
        fragmentCartInter.getCartDataNull();
    }

    @Override
    public void getCartDataSuccess(CartBean cartBean) {
        fragmentCartInter.getCartDataSuccess(cartBean);
    }
}
