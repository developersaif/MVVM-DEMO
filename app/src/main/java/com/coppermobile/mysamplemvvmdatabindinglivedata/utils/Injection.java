package com.coppermobile.mysamplemvvmdatabindinglivedata.utils;

import android.content.Context;
import androidx.annotation.NonNull;

import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.local.AppDatabase;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.local.LocalCommentsDataSource;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.local.LocalDishDataSource;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.remote.RemoteDishDataSource;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.remote.RemoteMarvelDataSource;
import com.coppermobile.mysamplemvvmdatabindinglivedata.repository.CommentsRepository;
import com.coppermobile.mysamplemvvmdatabindinglivedata.repository.DishRepository;
import com.coppermobile.mysamplemvvmdatabindinglivedata.repository.MarvelRepository;


public class Injection {

    public static DishRepository provideDishRepository(@NonNull Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        return DishRepository.getInstance(RemoteDishDataSource.getInstance(),
                LocalDishDataSource.getInstance(new AppExecutors(), database.dishDaoMethod()));
    }

    public static CommentsRepository provideCommentsRepository(@NonNull Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        return CommentsRepository.getInstance(LocalCommentsDataSource.getInstance(new AppExecutors(),
                database.commentsDoaMethod()));
    }



}