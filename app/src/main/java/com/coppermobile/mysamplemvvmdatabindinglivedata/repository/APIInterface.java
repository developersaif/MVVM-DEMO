package com.coppermobile.mysamplemvvmdatabindinglivedata.repository;


import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.remote.DishResponse;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.remote.MarvelResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("json/menu.json")
    Call<List<DishResponse>> getDishes();

    @GET("marvel")
    Call<List<MarvelResponse>> getMarvels();
}