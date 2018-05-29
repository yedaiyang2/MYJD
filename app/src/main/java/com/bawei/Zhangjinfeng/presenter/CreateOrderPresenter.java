package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.CreateOrderModel;
import com.bawei.Zhangjinfeng.model.bean.CreateOrderBean;
import com.bawei.Zhangjinfeng.presenter.inter.CreateOrderPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.CreateOrderInter;

/**
 * Created by Dash on 2018/2/25.
 */
public class CreateOrderPresenter implements CreateOrderPresenterInter {

    private CreateOrderInter createOrderInter;
    private CreateOrderModel createOrderModel;

    public CreateOrderPresenter(CreateOrderInter createOrderInter) {
        this.createOrderInter = createOrderInter;
        createOrderModel = new CreateOrderModel(this);
    }

    public void createOrder(String createOrderUrl, String uid, double price) {

        createOrderModel.createOrder(createOrderUrl,uid,price);

    }

    @Override
    public void onOrderCreateSuccess(CreateOrderBean createOrderBean) {
        createOrderInter.onCreateOrderSuccess(createOrderBean);
    }
}
