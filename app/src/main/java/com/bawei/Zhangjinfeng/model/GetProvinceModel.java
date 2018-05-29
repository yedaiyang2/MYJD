package com.bawei.Zhangjinfeng.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.Zhangjinfeng.model.bean.ProvinceBean;
import com.bawei.Zhangjinfeng.model.db.AddrDao;
import com.bawei.Zhangjinfeng.presenter.inter.GetProvincePresenterInter;

import java.util.ArrayList;
import java.util.List;


public class GetProvinceModel {


    private GetProvincePresenterInter getProvincePresenterInter;

    public GetProvinceModel(GetProvincePresenterInter getProvincePresenterInter) {
        this.getProvincePresenterInter = getProvincePresenterInter;
    }

    public void getProvince(Context context) {
        List<ProvinceBean> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = new AddrDao(context).initAddrDao();
        //select * from table where parentid = 0
        Cursor cursor = sqLiteDatabase.query("bma_regions", null, "parentid = 0", null, null, null, null);

        while (cursor.moveToNext()) {//能移动到下一个证明有数据

            int regionid = cursor.getInt(cursor.getColumnIndex("regionid"));//获取数据的方法里面的参数是列的角标...根据列名去获取列的角标
            String name = cursor.getString(cursor.getColumnIndex("name"));

            //封装province对象
            ProvinceBean provinceBean = new ProvinceBean(regionid, name);
            list.add(provinceBean);
        }

        //guan流
        cursor.close();
        sqLiteDatabase.close();

        //把集合回调回去
        getProvincePresenterInter.onGetProvince(list);

    }
}
