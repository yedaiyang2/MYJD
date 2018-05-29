package com.bawei.Zhangjinfeng.presenter;

import com.bawei.Zhangjinfeng.model.ProductListModel;
import com.bawei.Zhangjinfeng.model.bean.ProductListBean;
import com.bawei.Zhangjinfeng.presenter.inter.ProductListPresenterInter;
import com.bawei.Zhangjinfeng.view.Iview.ProductListActivityInter;

/**
 *
 */
public class ProductListPresenter implements ProductListPresenterInter {

    private ProductListModel productListModel;
    private ProductListActivityInter productListActivityInter;

    public ProductListPresenter(ProductListActivityInter productListActivityInter) {
        this.productListActivityInter = productListActivityInter;

        productListModel = new ProductListModel(this);
    }

    public void getProductData(String seartchUrl, String keywords, int page) {

        productListModel.getProductData(seartchUrl,keywords,page);
    }

    @Override
    public void onSuccess(ProductListBean productListBean) {
        productListActivityInter.getProductDataSuccess(productListBean);
    }
}
