package com.coppermobile.mysamplemvvmdatabindinglivedata.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Comments;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Dish;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Marvel;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.DataSource;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.MarvelDataSource;

import java.util.List;

public class MarvelRepository implements MarvelDataSource<Marvel> {

    private static MarvelRepository INSTANCE = null;
    private MarvelDataSource<Marvel> mRemoteDataSource;
   // private MarvelDataSource<Marvel> mLocalDataSource;
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
        mediatorLiveData.addSource(allDishesFromDao, dishList -> {
            mediatorLiveData.removeSource(allDishesFromDao);
            if (allDishesFromDao.getValue() != null) {
                if (MarvelRepository.this.shouldFetch(allDishesFromDao.getValue())) {

                    MarvelRepository.this.fetchFromNetwork(allDishesFromDao);
                } else {
                    mediatorLiveData.addSource(allDishesFromDao, dishList1 -> mediatorLiveData.setValue(dishList1));
                }
            }
        });

        return mediatorLiveData;
    }

    private void fetchFromNetwork(LiveData<List<Marvel>> data) {
        LiveData<List<Marvel>> allDishes = mRemoteDataSource.getAllMarvels();

        mediatorLiveData.addSource(data, dishList -> {
            //add loader
        });

        mediatorLiveData.addSource(allDishes, dishList -> {
            mediatorLiveData.removeSource(allDishes);
            mediatorLiveData.removeSource(data);
            if (dishList != null) {
                mRemoteDataSource.insertData(dishList);
                mediatorLiveData.addSource(mRemoteDataSource.getAllMarvels(), finalDishListFromDao -> mediatorLiveData.setValue(finalDishListFromDao));
            } else {
                //error case handle here
            }
        });
    }

    private boolean shouldFetch(List<Marvel> dishList) {
        return dishList == null || dishList.isEmpty();
    }
}


