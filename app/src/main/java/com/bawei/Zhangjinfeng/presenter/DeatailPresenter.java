package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.DeatilModel;
import com.bawei.Zhangjinfeng.model.bean.DeatilBean;
import com.bawei.Zhangjinfeng.presenter.inter.DeatilPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.DetailActivityInter;

/**
 * Created by Dash on 2018/1/24.
 */
public class DeatailPresenter implements DeatilPresenterInter{

    private DeatilModel deatilModel;
    private DetailActivityInter detailActivityInter;

    public DeatailPresenter(DetailActivityInter detailActivityInter) {
        this.detailActivityInter = detailActivityInter;

        deatilModel = new DeatilModel(this);

    }

    public void getDetailData(String detailUrl, int pid) {

        deatilModel.getDetailData(detailUrl,pid);
    }

    @Override
    public void onSuccess(DeatilBean deatilBean) {
        //回调给view
        detailActivityInter.onSuccess(deatilBean);
    }
}
