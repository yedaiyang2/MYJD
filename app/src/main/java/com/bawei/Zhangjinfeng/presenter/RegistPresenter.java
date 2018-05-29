package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.RegistModel;
import com.bawei.Zhangjinfeng.model.bean.RegistBean;
import com.bawei.Zhangjinfeng.presenter.inter.RegistPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.RegistActivityInter;


public class RegistPresenter implements RegistPresenterInter {

    private RegistActivityInter registActivityInter;
    private RegistModel registModel;

    public RegistPresenter(RegistActivityInter registActivityInter) {
        this.registActivityInter = registActivityInter;
        registModel = new RegistModel(this);
    }

    public void registUser(String registUrl, String name, String pwd) {

        registModel.registUser(registUrl,name,pwd);
    }

    @Override
    public void onRegistSuccess(RegistBean registBean) {
        registActivityInter.onRegistSuccess(registBean);
    }
}
