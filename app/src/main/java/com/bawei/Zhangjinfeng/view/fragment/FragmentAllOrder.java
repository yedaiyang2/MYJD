package com.bawei.Zhangjinfeng.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bawei.Zhangjinfeng.R;
import com.bawei.Zhangjinfeng.model.bean.OrderListBean;
import com.bawei.Zhangjinfeng.presenter.OrderListPresenter;
import com.bawei.Zhangjinfeng.util.ApiUtil;
import com.bawei.Zhangjinfeng.util.CommonUtils;
import com.bawei.Zhangjinfeng.view.Iview.FragmentOrderListInter;
import com.bawei.Zhangjinfeng.view.adapter.OrderListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentAllOrder extends Fragment implements FragmentOrderListInter {

    private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private OrderListPresenter orderListPresenter;
    //分页
    private int page = 1;
    //大集合
    private List<OrderListBean.DataBean> listAll = new ArrayList<>();
    private OrderListAdapter orderListAdapter;
    private RelativeLayout relative_empty_order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_all_layout,container,false);
        recyclerView = view.findViewById(R.id.recycler_order);
        smartRefreshLayout = view.findViewById(R.id.smart_refresh);
        relative_empty_order = view.findViewById(R.id.relative_empty_order);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //获取订单列表的数据
        orderListPresenter = new OrderListPresenter(this);
        orderListPresenter.getOrderData(ApiUtil.ORDER_LIST_URL, CommonUtils.getString("uid"),page);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smartRefreshLayout.finishRefresh(2000);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page ++;
                orderListPresenter.getOrderData(ApiUtil.ORDER_LIST_URL, CommonUtils.getString("uid"),page);
            }
        });
    }


    @Override
    public void onOrderDataSuccess(OrderListBean orderListBean) {
        if ("0".equals(orderListBean.getCode())) {
            //添加到大集合
            listAll.addAll(orderListBean.getData());

            if (listAll.size() == 0) {
                relative_empty_order.setVisibility(View.VISIBLE);
                smartRefreshLayout.setVisibility(View.GONE);
            }else {
                relative_empty_order.setVisibility(View.GONE);
                smartRefreshLayout.setVisibility(View.VISIBLE);
            }

            //设置适配器
            setAdapter();

        }
    }

    /**
     * 设置适配器
     */
    private void setAdapter() {

        if (orderListAdapter == null) {
            orderListAdapter = new OrderListAdapter(getActivity(), listAll);
            recyclerView.setAdapter(orderListAdapter);
        }else {
            orderListAdapter.notifyDataSetChanged();
        }

        smartRefreshLayout.finishLoadmore();

    }
}
