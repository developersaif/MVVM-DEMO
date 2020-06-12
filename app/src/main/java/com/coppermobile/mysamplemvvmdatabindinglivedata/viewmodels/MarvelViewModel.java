package com.coppermobile.mysamplemvvmdatabindinglivedata.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Dish;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Marvel;
import com.coppermobile.mysamplemvvmdatabindinglivedata.repository.DishRepository;
import com.coppermobile.mysamplemvvmdatabindinglivedata.repository.MarvelRepository;

import java.util.List;

public class MarvelViewModel extends AndroidViewModel {

    private final MarvelRepository mTasksRepository;

    public MarvelViewModel(Application context, MarvelRepository repository) {
        super(context);
        mTasksRepository = repository;
    }

    public LiveData<List<Marvel>> getAllMarvel() {
            return mTasksRepository.getAllMarvels();
    }
}