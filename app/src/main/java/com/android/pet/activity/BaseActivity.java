package com.android.pet.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        setContentView(getContentView());
        initView();
        initData();
        initAction();
    }

    protected abstract void initAction();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getContentView();

    protected <T extends View> T fd(@IdRes int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ImmersionBar.with(this).init();
    }
}
