package com.bawei.Zhangjinfeng.view.Iview;

import com.bawei.Zhangjinfeng.model.bean.FenLeiBean;
import com.bawei.Zhangjinfeng.model.bean.HomeBean;


public interface InterFragmentHome {
    void onSuccess(HomeBean homeBean);

    void onFenLeiDataSuccess(FenLeiBean fenLeiBean);
}
