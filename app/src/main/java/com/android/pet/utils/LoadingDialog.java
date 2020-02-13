package com.android.pet.utils;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.kaopiz.kprogresshud.KProgressHUD;

public class LoadingDialog {
    @SuppressLint("StaticFieldLeak")
    private static KProgressHUD progressHUD;

    /**
     * 显示等待框
     *
     * @param activity activity
     */
    public static void show(Activity activity, String msg) {
        progressHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDetailsLabel(msg)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    /**
     * 隐藏等待框
     */
    public static void dismiss() {
        if (progressHUD != null && progressHUD.isShowing()) {
            progressHUD.dismiss();
            progressHUD = null;
        }
    }
}
