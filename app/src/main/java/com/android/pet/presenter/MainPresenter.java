package com.android.pet.presenter;

import com.android.pet.http.RetrofitUtil;
import com.android.pet.model.Pet;
import com.android.pet.service.ApiService;
import com.android.pet.view.IBaseView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {
    private IBaseView<List<Pet>> baseView;

    public MainPresenter(IBaseView<List<Pet>> baseView) {
        this.baseView = baseView;
    }

    public void getPetList(String msg, final boolean flag) {
        if (flag) {
            baseView.showLoading(msg);
        }
        ApiService apiService = RetrofitUtil.getInstance().getApiService();
        Observable<List<Pet>> observable = apiService.petList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Pet>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Pet> value) {
                        baseView.success(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (flag) {
                            baseView.hideLoading();
                        }
                        baseView.error(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (flag) {
                            baseView.hideLoading();
                        }
                    }
                });
    }
}
