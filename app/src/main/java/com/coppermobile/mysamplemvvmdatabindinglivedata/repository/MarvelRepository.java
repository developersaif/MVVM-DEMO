package com.coppermobile.mysamplemvvmdatabindinglivedata.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Marvel;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.MarvelDataSource;

import java.util.List;

public class MarvelRepository implements MarvelDataSource<Marvel> {

    private static MarvelRepository INSTANCE = null;
    private MarvelDataSource<Marvel> mRemoteDataSource;
    private MediatorLiveData<List<Marvel>> mediatorLiveData;

    private MarvelRepository(@NonNull MarvelDataSource<Marvel> mRemoteDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;

    }

    public static MarvelRepository getInstance(MarvelDataSource<Marvel> tasksRemoteDataSource) {
        if (INSTANCE == null) {
            synchronized (MarvelRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MarvelRepository(tasksRemoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void insertData(Marvel data) {
    }

    @Override
    public void insertData(List<Marvel> data) {
    }

    @Override
    public void deleteData(Marvel data) {
    }

    @Override
    public LiveData<List<Marvel>> getAllMarvels() {
        mediatorLiveData = new MediatorLiveData<>();
        LiveData<List<Marvel>> allDishesFromDao = mRemoteDataSource.getAllMarvels();
        mediatorLiveData.addSource(allDishesFromDao, new Observer<List<Marvel>>() {
            @Override
            public void onChanged(@Nullable List<Marvel> marvelList) {
              //  mediatorLiveData.removeSource(allDishesFromDao);
                if (allDishesFromDao.getValue() != null) {
                    if (MarvelRepository.this.shouldFetch(allDishesFromDao.getValue())) {

                        MarvelRepository.this.fetchFromNetwork(allDishesFromDao);
                    } else {
                        mediatorLiveData.addSource(allDishesFromDao, marvelList1 -> mediatorLiveData.setValue(marvelList1));
                    }
                }
            }
        });

        return mediatorLiveData;
    }

    private void fetchFromNetwork(LiveData<List<Marvel>> data) {
        LiveData<List<Marvel>> allDishes = mRemoteDataSource.getAllMarvels();

        mediatorLiveData.addSource(data, new Observer<List<Marvel>>() {
            @Override
            public void onChanged(@Nullable List<Marvel> marvelList) {
                //add loader
            }
        });

        mediatorLiveData.addSource(allDishes, marvelList -> {
            mediatorLiveData.removeSource(allDishes);
            mediatorLiveData.removeSource(data);
            if (marvelList != null) {
                  mRemoteDataSource.insertData(marvelList);
                mediatorLiveData.addSource(mRemoteDataSource.getAllMarvels(), finalDishListFromDao -> mediatorLiveData.setValue(finalDishListFromDao));
            } else {
                //error case handle here
            }
        });
    }

    private boolean shouldFetch(List<Marvel> marvelList) {
        return marvelList == null || marvelList.isEmpty();
    }
}


