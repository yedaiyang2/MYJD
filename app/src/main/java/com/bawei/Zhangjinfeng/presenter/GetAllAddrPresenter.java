package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.GetAllAddrModel;
import com.bawei.Zhangjinfeng.model.bean.GetAllAddrBean;
import com.bawei.Zhangjinfeng.presenter.inter.GetAllAddrPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.GetAllAddrInter;

/**
 * Created by Dash on 2018/2/27.
 */
public class GetAllAddrPresenter implements GetAllAddrPresenterInter {

    private GetAllAddrInter getAllAddrInter;
    private GetAllAddrModel getAllAddrModel;

    public GetAllAddrPresenter(GetAllAddrInter getAllAddrInter) {
        this.getAllAddrInter = getAllAddrInter;
        getAllAddrModel = new GetAllAddrModel(this);
    }

    public void getAllAddr(String getAllAddrUrl, String uid) {
        getAllAddrModel.getAllAddr(getAllAddrUrl,uid);
    }

    @Override
    public void onGetAllAddrSuccess(GetAllAddrBean getAllAddrBean) {
        getAllAddrInter.onGetAllAddrSuccess(getAllAddrBean);
    }
}
