package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.FragmentFenLeiRightModel;
import com.bawei.Zhangjinfeng.model.bean.ChildFenLeiBean;
import com.bawei.Zhangjinfeng.presenter.inter.FenLeiRightPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.FenLeiRightInter;

/**
 *
 */
public class FragmentFenLeiRightPresenter implements FenLeiRightPresenterInter {

    private FenLeiRightInter fenLeiRightInter;
    private FragmentFenLeiRightModel fragmentFenLeiRightModel;

    public FragmentFenLeiRightPresenter(FenLeiRightInter fenLeiRightInter) {
        this.fenLeiRightInter = fenLeiRightInter;

        fragmentFenLeiRightModel = new FragmentFenLeiRightModel(this);
    }

    public void getChildData(String childFenLeiUrl, int cid) {

        fragmentFenLeiRightModel.getChildData(childFenLeiUrl,cid);
    }

    @Override
    public void onSuncess(ChildFenLeiBean childFenLeiBean) {

        fenLeiRightInter.getSuccessChildData(childFenLeiBean);

    }
}
