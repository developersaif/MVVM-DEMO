package com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.remote;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Comments;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Dish;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Marvel;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.DataSource;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.MarvelDataSource;
import com.coppermobile.mysamplemvvmdatabindinglivedata.repository.APIInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteMarvelDataSource implements MarvelDataSource<Marvel> {

    private static final String URL = "https://www.simplifiedcoding.net/demos/";
    private static APIInterface dishAPIInterface;
    private static RemoteMarvelDataSource INSTANCE;

    private RemoteMarvelDataSource() {
    }

    public synchronized static RemoteMarvelDataSource getInstance() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        dishAPIInterface = retrofit.create(APIInterface.class);

        if (INSTANCE == null) {
            INSTANCE = new RemoteMarvelDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void insertData(Marvel data) {
        //No use here
    }

    @Override
    public void insertData(List<Marvel> data) {

    }

    @Override
    public void deleteData(Marvel data) {

    }


    @Override
    public LiveData<List<Marvel>> getAllMarvels() {
        final MutableLiveData<List<Marvel>> mutableLiveData = new MutableLiveData<>();
        dishAPIInterface.getMarvels().enqueue(new Callback<List<MarvelResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<MarvelResponse>> call, @NonNull Response<List<MarvelResponse>> response) {
                if (response.body() != null) {
                    List<MarvelResponse> dishResponseList = response.body();
                    if (dishResponseList != null) {
                        List<Marvel> dishList = new ArrayList<>();
                        for (int i = 0; i < dishResponseList.size(); i++) {
                            Marvel dish = new Marvel();
                            dish.setName(dishResponseList.get(i).getName());
                            dish.setRealname(dishResponseList.get(i).getRealname());
                            dish.setTeam(dishResponseList.get(i).getTeam());
                            dish.setFirstappearance(dishResponseList.get(i).getFirstappearance());
                            dish.setCreatedby(dishResponseList.get(i).getCreatedby());
                            dish.setPublisher(dishResponseList.get(i).getPublisher());
                            dish.setImageurl(dishResponseList.get(i).getImageurl());
                            dish.setBio(dishResponseList.get(i).getBio());
                            dishList.add(dish);
                        }
                        mutableLiveData.setValue(dishList);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MarvelResponse>> call, @NonNull Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}