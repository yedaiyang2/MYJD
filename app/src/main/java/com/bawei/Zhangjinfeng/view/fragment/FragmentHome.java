package com.bawei.Zhangjinfeng.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bawei.Zhangjinfeng.R;
import com.bawei.Zhangjinfeng.model.bean.FenLeiBean;
import com.bawei.Zhangjinfeng.model.bean.HomeBean;
import com.bawei.Zhangjinfeng.presenter.FragmentHomeP;
import com.bawei.Zhangjinfeng.util.ApiUtil;
import com.bawei.Zhangjinfeng.util.ChenJinUtil;
import com.bawei.Zhangjinfeng.util.GlideImageLoader;
import com.bawei.Zhangjinfeng.view.Iview.InterFragmentHome;
import com.bawei.Zhangjinfeng.view.Iview.OnItemListner;
import com.bawei.Zhangjinfeng.view.activity.CustomCaptrueActivity;
import com.bawei.Zhangjinfeng.view.activity.DetailActivity;
import com.bawei.Zhangjinfeng.view.activity.SousuoActivity;
import com.bawei.Zhangjinfeng.view.activity.WebViewActivity;
import com.bawei.Zhangjinfeng.view.adapter.HengXiangAdapter;
import com.bawei.Zhangjinfeng.view.adapter.MiaoShaAdapter;
import com.bawei.Zhangjinfeng.view.adapter.TuiJianAdapter;
import com.bawei.Zhangjinfeng.view.custom.ObservableScrollView;
import com.bawei.zxinglibrary.activity.CodeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment implements InterFragmentHome{

    private FragmentHomeP fragmentHomeP;
    private Banner banner;
    private RecyclerView heng_xiang;
    private RecyclerView tui_jian_recycler;
    private MarqueeView marqueeView;
    private RecyclerView miao_sha_recycler;
    private SmartRefreshLayout smart_refresh;
    private LinearLayout linear_include;
    private final String TAG_MARGIN_ADDED = "marginAdded";
    private ObservableScrollView observe_scroll_view;
    private LinearLayout sao_yi_sao;
    private LinearLayout sousuo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //主要在oncreateView中做的操作是找到控件...
        View view = inflater.inflate(R.layout.fragment_home_layout,container,false);

        banner = view.findViewById(R.id.banner);
        heng_xiang = view.findViewById(R.id.heng_xiang_recycler);
        tui_jian_recycler = view.findViewById(R.id.tui_jian_recycler);
        marqueeView = (MarqueeView) view.findViewById(R.id.marqueeView);
        miao_sha_recycler = view.findViewById(R.id.miao_sha_recycler);
        smart_refresh = view.findViewById(R.id.smart_refresh);
        linear_include = view.findViewById(R.id.linear_include);
        observe_scroll_view = view.findViewById(R.id.observe_scroll_view);
        sao_yi_sao = view.findViewById(R.id.sao_yi_sao);
        sousuo = view.findViewById(R.id.sousuo);
       /* try {
            int i = 1/0;//此处存在异常,,,但是没有捕获,,,运行起来之后程序会崩溃
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ChenJinUtil.setStatusBarColor(getActivity(), Color.TRANSPARENT);

        //标题栏
        initTitleBar();

        //在这里可以做其他操作....
        fragmentHomeP = new FragmentHomeP(this);

        //调用p层里面的方法....https://www.zhaoapi.cn/ad/getAd
        fragmentHomeP.getNetData(ApiUtil.HOME_URL);

        //一个view一般是对应着一个presenter和一个model;;;;逻辑比较复杂的页面可能会对应多个presenter和多个model
        fragmentHomeP.getFenLeiData(ApiUtil.FEN_LEI_URL);


        //初始化banner
        initBanner();

        //初始化跑马灯
        initMarqueeView();

        smart_refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                smart_refresh.finishLoadmore(2000);
            }
        });
        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                smart_refresh.finishRefresh(2000);
            }
        });

        sao_yi_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=  new Intent(getActivity(), CustomCaptrueActivity.class);
                startActivityForResult(intent, 1001);
            }
        });
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=  new Intent(getActivity(), SousuoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTitleBar() {

        //linearLayout在view绘制的时候外面包裹一层relativeLayout
        //应该尽量减少使用linearLayout...view优化

        addMargin();


        ViewTreeObserver viewTreeObserver = linear_include.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linear_include.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                final int height = linear_include.getHeight();

                observe_scroll_view.setScrollViewListener(new ObservableScrollView.IScrollViewListener() {
                    @Override
                    public void onScrollChanged(int x, int y, int oldx, int oldy) {
                        if (y <= 0) {
                            addMargin();
                            ChenJinUtil.setStatusBarColor(getActivity(),Color.TRANSPARENT);

                            linear_include.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或
                        } else if (y > 0 && y < height) {

                            if (y > ChenJinUtil.getStatusBarHeight(getActivity())) {
                                //去掉margin
                                removeMargin();
                                //状态栏为灰色
                                ChenJinUtil.setStatusBarColor(getActivity(),getResources().getColor(R.color.colorPrimaryDark));
                            }

                            float scale = (float) y / height;
                            float alpha = (255 * scale);
                            // 只是layout背景透明(仿知乎滑动效果)
                            linear_include.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                        } else {
                            linear_include.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                        }
                    }
                });
            }
        });

    }

    private void removeMargin() {
        if (TAG_MARGIN_ADDED.equals(linear_include.getTag())) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linear_include.getLayoutParams();
            // 移除的间隔大小就是状态栏的高度
            params.topMargin -= ChenJinUtil.getStatusBarHeight(getActivity());
            linear_include.setLayoutParams(params);
            linear_include.setTag(null);
        }
    }

    private void addMargin() {
        //给标题调价margin
        if (!TAG_MARGIN_ADDED.equals(linear_include.getTag())) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) linear_include.getLayoutParams();
            // 添加的间隔大小就是状态栏的高度
            params.topMargin += ChenJinUtil.getStatusBarHeight(getActivity());
            linear_include.setLayoutParams(params);
            linear_include.setTag(TAG_MARGIN_ADDED);
        }
    }


    private void initMarqueeView() {
        List<String> info = new ArrayList<>();
        info.add("欢迎访问京东app");
        info.add("去问问");
        info.add("qwer");

        marqueeView.startWithList(info);
    }

    private void initBanner() {

        //设置banner样式...CIRCLE_INDICATOR_TITLE包含标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (! hidden) {

            //可见的时候...当前位置>stusbar
            if (observe_scroll_view.getScrollY() > ChenJinUtil.getStatusBarHeight(getActivity())) {
                removeMargin();
                ChenJinUtil.setStatusBarColor(getActivity(),getResources().getColor(R.color.colorPrimaryDark));
            }else {
                addMargin();
                ChenJinUtil.setStatusBarColor(getActivity(),Color.TRANSPARENT);
            }
        }
    }


    //此时已经获取导数据,,,,并且此时也在主线程中
    @Override
    public void onSuccess(final HomeBean homeBean) {

        //设置显示bannner
        List<HomeBean.DataBean> datas = homeBean.getData();

        List<String> imageUrls = new ArrayList<>();
        for (int i = 0;i<datas.size();i++){
            imageUrls.add(datas.get(i).getIcon());
        }

        banner.setImages(imageUrls);

        //banner的点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

                HomeBean.DataBean dataBean = homeBean.getData().get(position);
                if (dataBean.getType() == 0) {
                    //跳转webView
                    Intent intent = new Intent(getActivity(),WebViewActivity.class);

                    intent.putExtra("detailUrl",dataBean.getUrl());
                    startActivity(intent);

                }else {
                    Toast.makeText(getActivity(),"即将跳转商品详情",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //放在最后进行start 否则点击事件会有问题
        banner.start();

        //秒杀的数据
        miao_sha_recycler.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.HORIZONTAL,false));

        MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(getActivity(), homeBean.getMiaosha());
        miao_sha_recycler.setAdapter(miaoShaAdapter);
        //条目的点击事件
        miaoShaAdapter.setOnItemListner(new OnItemListner() {
            @Override
            public void onItemClick(int position) {
                //跳转到下一个页面....传值过去...webView页面
                Intent intent = new Intent(getActivity(), WebViewActivity.class);

                String detailUrl = homeBean.getMiaosha().getList().get(position).getDetailUrl();
                intent.putExtra("detailUrl",detailUrl);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

        //瀑布流
        tui_jian_recycler.setLayoutManager(new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL));
        //为你推荐设置适配器
        TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(getActivity(), homeBean.getTuijian());
        tui_jian_recycler.setAdapter(tuiJianAdapter);

        //为你推荐的点击事件
        tuiJianAdapter.setOnItemListner(new OnItemListner() {
            @Override
            public void onItemClick(int position) {

                //跳转的是自己的详情页面
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                //传递pid
                intent.putExtra("pid",homeBean.getTuijian().getList().get(position).getPid());
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

    }

    @Override
    public void onFenLeiDataSuccess(FenLeiBean fenLeiBean) {
        //在这里拿到了分类的数据

        //如果要展示数据的话必须先设置布局管理器....上下文,,,表示几行或者几列,,,表示方向,水平或者竖直,,,是否反转布局展示
        heng_xiang.setLayoutManager(new GridLayoutManager(getActivity(),2, OrientationHelper.HORIZONTAL,false));

        HengXiangAdapter hengXiangAdapter = new HengXiangAdapter(getActivity(), fenLeiBean);
        //设置适配器
        heng_xiang.setAdapter(hengXiangAdapter);

        //设置条目的点击事件...实际是适配器的点击事件
        hengXiangAdapter.setOnItemListner(new OnItemListner() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(),"点击事件执行",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(getActivity(),"长按事件执行",Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                //拿到传递回来的数据
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }

                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    //解析得到的结果
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    if (result.startsWith("http://")) {
                        Intent intent = new Intent(getActivity(),WebViewActivity.class);
                        intent.putExtra("detailUrl",result);
                        startActivity(intent);
                    }else {

                        Toast.makeText(getActivity(), "暂不支持此二维码", Toast.LENGTH_LONG).show();
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
