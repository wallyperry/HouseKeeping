package com.cjcm.housekeeping.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cjcm.housekeeping.R;
import com.cjcm.housekeeping.adapter.ProductRvAdapter;
import com.cjcm.housekeeping.base.BaseFragment;
import com.cjcm.housekeeping.bean.ProductBean;
import com.cjcm.housekeeping.mvp.contract.ProductContract;
import com.cjcm.housekeeping.mvp.presenter.ProductPresenter;
import com.cjcm.housekeeping.net.ApiException;
import com.cjcm.housekeeping.utils.UiUtils;

import butterknife.Bind;

/**
 * @Author Perry
 * @Wechat 917351143
 * @Date 2018/6/27
 */
public class ProductTypeFragment extends BaseFragment<ProductPresenter> implements ProductContract.View,
        BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {
    private final int pageSize = 10;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private String type;
    private int pageCount = 1;
    private ProductRvAdapter rvAdapter;
    private boolean isLoadMore = false;

    private View errorView;
    private TextView tvErrorView;

    public static Fragment newInstance(String type) {
        Fragment fragment = new ProductTypeFragment();
        Bundle arg = new Bundle();
        arg.putString("type", type);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    protected void initData(Bundle arguments) {
        super.initData(arguments);
        type = arguments.getString("type");
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_product_type;
    }

    @Override
    protected ProductPresenter onCreatePresenter() {
        return new ProductPresenter(this);
    }

    @Override
    protected void initView() {
        errorView = UiUtils.getView(R.layout.view_rv_empty);
        tvErrorView = errorView.findViewById(R.id.tvMsg);

        refreshLayout.setColorSchemeColors(UiUtils.getAppColor());
        refreshLayout.setOnRefreshListener(() -> {
            pageCount = 1;
            fetchData();
        });
        refreshLayout.setRefreshing(true);

        rvAdapter = new ProductRvAdapter();
        rvAdapter.bindToRecyclerView(recyclerView);
        rvAdapter.openLoadAnimation();
        rvAdapter.setOnItemChildClickListener(this);
        rvAdapter.setEnableLoadMore(true);
        rvAdapter.setOnLoadMoreListener(this, recyclerView);
        rvAdapter.disableLoadMoreIfNotFullPage(recyclerView);

        LinearLayoutManager lm = new LinearLayoutManager(activity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    protected void fetchData() {
        mPresenter.productData(pageCount, pageSize, type);
    }

    @Override
    public void onProductSuccess(ProductBean bean) {
        refreshLayout.setRefreshing(false);
        if (bean.isSuccess()) {
            int size = bean.getBody().getPage().getList().size();
            if (isLoadMore) {
                isLoadMore = false;
                if (size >= 1) {
                    rvAdapter.addData(bean.getBody().getPage().getList());
                    rvAdapter.loadMoreComplete();
                } else {
                    rvAdapter.loadMoreFail();
                }
            } else {
                //首次加载数据
                if (size >= 1) {
                    rvAdapter.setNewData(bean.getBody().getPage().getList());
                } else {
                    rvAdapter.setNewData(null);
                    rvAdapter.setEmptyView(errorView);
                    tvErrorView.setText(bean.getMsg());
                }
            }
            /*
             * 判断已是最后一页了
             */
            if (bean.getBody().getPage().getPageNo() * pageSize >= bean.getBody().getPage().getCount()) {
                rvAdapter.loadMoreEnd(false);
            }
        } else {
            listDataError(bean.getMsg());
        }
    }

    @Override
    public void onProductError(ApiException.ResponseException e) {
        refreshLayout.setRefreshing(false);
        listDataError(e.message);
    }

    /**
     * 处理列表数据请求失败的方法
     *
     * @param msg 错误信息
     */
    private void listDataError(String msg) {
        if (isLoadMore) {
            isLoadMore = false;
            rvAdapter.loadMoreFail();
        } else {
            rvAdapter.setNewData(null);
            rvAdapter.setEmptyView(errorView);
            tvErrorView.setText(msg);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        pageCount++;
        isLoadMore = true;
        fetchData();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ProductBean.BodyBean.PageBean.ListBean bean = (ProductBean.BodyBean.PageBean.ListBean) adapter.getData().get(position);
        switch (view.getId()) {
            case R.id.cv:
                startActivity(ProductDetailActivity.class, "bean", bean);
                overridePendingTransition(MOVE_LEFT);
                break;
        }
    }
}
