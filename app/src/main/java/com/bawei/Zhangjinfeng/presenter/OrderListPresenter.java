package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.OrderListModel;
import com.bawei.Zhangjinfeng.model.bean.OrderListBean;
import com.bawei.Zhangjinfeng.presenter.inter.OrderListPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.FragmentOrderListInter;

/**
 * Created by Dash on 2018/2/25.
 */
public class OrderListPresenter implements OrderListPresenterInter {

    private FragmentOrderListInter fragmentOrderListInter;
    private OrderListModel orderListModel;

    public OrderListPresenter(FragmentOrderListInter fragmentOrderListInter) {
        this.fragmentOrderListInter = fragmentOrderListInter;
        orderListModel = new OrderListModel(this);
    }

    public void getOrderData(String orderListUrl, String uid, int page) {

        orderListModel.getOrderData(orderListUrl,uid,page);

    }

    @Override
    public void onOrderDataSuccess(OrderListBean orderListBean) {

        fragmentOrderListInter.onOrderDataSuccess(orderListBean);
    }
}
