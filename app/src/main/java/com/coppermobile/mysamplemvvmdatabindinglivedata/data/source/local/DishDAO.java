package com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Dish;

import java.util.List;

@Dao
interface DishDAO {

    @Insert
    void insertDishes(List<Dish> dishList);

    @Query("select * from dish")
    LiveData<List<Dish>> getAllDishes();
}