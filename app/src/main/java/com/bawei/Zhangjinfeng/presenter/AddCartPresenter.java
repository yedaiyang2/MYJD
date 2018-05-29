package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.AddCartModel;
import com.bawei.Zhangjinfeng.model.bean.AddCartBean;
import com.bawei.Zhangjinfeng.presenter.inter.AddCartPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.ActivityAddCartInter;

/**
 * Created by Dash on 2018/2/1.
 */
public class AddCartPresenter implements AddCartPresenterInter {

    private ActivityAddCartInter activityAddCartInter;
    private AddCartModel addCartModel;

    public AddCartPresenter(ActivityAddCartInter activityAddCartInter) {
        this.activityAddCartInter = activityAddCartInter;

        addCartModel = new AddCartModel(this);
    }

    public void addToCart(String addCart, String uid, int pid) {

        addCartModel.addToCart(addCart,uid,pid);

    }

    @Override
    public void onCartAddSuccess(AddCartBean addCartBean) {
        activityAddCartInter.onCartAddSuccess(addCartBean);
    }
}
