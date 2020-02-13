package com.android.pet.service;

import com.android.pet.model.Pet;
import com.android.pet.model.PetDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    /**
     * 宠物列表
     *
     * @return
     */
    @GET("pets")
    Observable<List<Pet>> petList();

    /**
     * 宠物详情
     *
     * @param id ID
     * @return
     */
    @GET("pet/{id}")
    Observable<PetDetail> petDetail(@Path("id") int id);
}
