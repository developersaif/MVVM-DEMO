package com.coppermobile.mysamplemvvmdatabindinglivedata.data.source;

import androidx.lifecycle.LiveData;

import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Comments;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Dish;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Marvel;

import java.util.List;

public interface MarvelDataSource<T> {

    void insertData(T data);

    void insertData(List<T> data);

    void deleteData(T data);

    LiveData<List<Marvel>> getAllMarvels();
}