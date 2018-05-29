package com.bawei.Zhangjinfeng.presenter.inter;

import com.bawei.Zhangjinfeng.model.bean.FenLeiBean;
import com.bawei.Zhangjinfeng.model.bean.HomeBean;


public interface FragmentHomePInter {
    //首页的数据
    void onSuccess(HomeBean homeBean);
    //分类
    void onFenLeiDataSuccess(FenLeiBean fenLeiBean);
}
