package com.android.pet.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.pet.R;
import com.android.pet.http.RetrofitUtil;
import com.android.pet.model.PetDetail;
import com.android.pet.service.ApiService;
import com.android.pet.utils.LoadingDialog;
import com.bumptech.glide.Glide;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PetDetailActivity extends BaseActivity {
    private ImageView ivBack;
    private TextView tvTitleName;
    private TextView tvName;
    private ImageView ivIcon;
    private TextView tvUseCount;
    private TextView tvLikeCount;
    private int id;

    public static void startActivity(Activity activity, int id) {
        Intent intent = new Intent(activity, PetDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    @Override
    protected void initAction() {
        getDetail(id);
    }

    private void getDetail(int id) {
        if (id == 0) {
            Toast.makeText(this, "id错误", Toast.LENGTH_SHORT).show();
            return;
        }
        LoadingDialog.show(this, "正在加载中...");
        ApiService apiService = RetrofitUtil.getInstance().getApiService();
        Observable<PetDetail> observable = apiService.petDetail(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PetDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(PetDetail value) {
                        tvTitleName.setText(value.getName());
                        tvName.setText(value.getName());
                        if (!TextUtils.isEmpty(value.getImage())) {
                            Glide.with(PetDetailActivity.this).load(value.getImage()).into(ivIcon);
                        }
                        tvUseCount.setText("使用人数：" + value.getUseCount());
                        tvLikeCount.setText("喜欢人数：" + value.getLikeCount());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoadingDialog.dismiss();
                        Toast.makeText(PetDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        LoadingDialog.dismiss();
                    }
                });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    @Override
    protected void initView() {
        ivBack = fd(R.id.iv_back);
        tvTitleName = fd(R.id.tv_title_name);
        tvName = fd(R.id.tv_name);
        ivIcon = fd(R.id.iv_icon);
        tvUseCount = fd(R.id.tv_use_count);
        tvLikeCount = fd(R.id.tv_like_count);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_pet_detail;
    }
}
