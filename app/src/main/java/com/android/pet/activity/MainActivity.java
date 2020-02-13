package com.android.pet.activity;

import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.pet.R;
import com.android.pet.adapter.MainAdapter;
import com.android.pet.model.Pet;
import com.android.pet.presenter.MainPresenter;
import com.android.pet.utils.LoadingDialog;
import com.android.pet.view.IBaseView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MainActivity extends BaseActivity implements XRecyclerView.LoadingListener, IBaseView<List<Pet>> {
    private XRecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private MainPresenter mainPresenter;
    private boolean isRefresh = false;

    @Override
    protected void initAction() {
        mainPresenter = new MainPresenter(this);
        mainPresenter.getPetList("正在获取宠物列表...", true);
        recyclerView.setLoadingListener(this);
    }

    @Override
    protected void initData() {
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initView() {
        recyclerView = fd(R.id.recyclerView);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        mainPresenter.getPetList("正在获取宠物列表...", false);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void success(List<Pet> result) {
        if (isRefresh) {
            recyclerView.refreshComplete();
            mainAdapter.notifyDataSetChanged();
            return;
        }
        mainAdapter = new MainAdapter(result, this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void error(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(String msg) {
        LoadingDialog.show(this, msg);
    }

    @Override
    public void hideLoading() {
        LoadingDialog.dismiss();
    }
}
