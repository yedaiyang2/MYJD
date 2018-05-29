package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.UserInfoModel;
import com.bawei.Zhangjinfeng.model.bean.UserInfoBean;
import com.bawei.Zhangjinfeng.presenter.inter.UserInfoPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.UserInforInter;


public class UserInfoPresenter implements UserInfoPresenterInter {

    private final UserInfoModel userInfoModel;
    private final UserInforInter userInforInter;

    public UserInfoPresenter(UserInforInter userInforInter) {
        this.userInforInter = userInforInter;
        userInfoModel = new UserInfoModel(this);
    }

    public void getUserInfo(String userInfoUrl, String uid) {

        userInfoModel.getUserInfo(userInfoUrl,uid);

    }

    @Override
    public void onUserInfoSUccess(UserInfoBean userInfoBean) {
        userInforInter.onUserInforSuccess(userInfoBean);
    }
}
