package com.android.pet.view;

public interface IBaseView<R> {
    /**
     * 成功
     * @param result 结果
     */
    void success(R result);

    /**
     * 失败
     * @param msg 错误信息
     */
    void error(String msg);

    /**
     * 加载框
     * @param msg 加载信息
     */
    void showLoading(String msg);

    /**
     * 隐藏加载框
     */
    void hideLoading();
}
