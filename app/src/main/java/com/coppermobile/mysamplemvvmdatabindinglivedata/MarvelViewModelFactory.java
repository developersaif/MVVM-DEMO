package com.coppermobile.mysamplemvvmdatabindinglivedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.coppermobile.mysamplemvvmdatabindinglivedata.repository.MarvelRepository;
import com.coppermobile.mysamplemvvmdatabindinglivedata.utils.Injection;
import com.coppermobile.mysamplemvvmdatabindinglivedata.viewmodels.CommentsViewModel;
import com.coppermobile.mysamplemvvmdatabindinglivedata.viewmodels.DishViewModel;
import com.coppermobile.mysamplemvvmdatabindinglivedata.viewmodels.MarvelViewModel;

public class MarvelViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static MarvelViewModelFactory INSTANCE;
    private final Application mApplication;
    MarvelRepository marvelRepository;

    public static MarvelViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (MarvelViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MarvelViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private MarvelViewModelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MarvelViewModel.class)) {
            //noinspection unchecked
             return (T) new MarvelViewModel(mApplication,marvelRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}