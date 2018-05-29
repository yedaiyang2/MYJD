package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.UpLoadPicModel;
import com.bawei.Zhangjinfeng.model.bean.UpLoadPicBean;
import com.bawei.Zhangjinfeng.presenter.inter.UpLoadPicPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.UpLoadActivityInter;

import java.io.File;


public class UpLoadPicPresenter implements UpLoadPicPresenterInter {

    private UpLoadPicModel upLoadPicModel;
    private UpLoadActivityInter upLoadActivityInter;

    public UpLoadPicPresenter(UpLoadActivityInter upLoadActivityInter) {
        this.upLoadActivityInter = upLoadActivityInter;
        upLoadPicModel = new UpLoadPicModel(this);
    }

    public void uploadPic(String uploadIconUrl, File saveIconFile, String uid, String fileName) {

        upLoadPicModel.uploadPic(uploadIconUrl,saveIconFile,uid,fileName);

    }

    @Override
    public void uploadPicSuccess(UpLoadPicBean upLoadPicBean) {
        upLoadActivityInter.uploadPicSuccess(upLoadPicBean);
    }
}
