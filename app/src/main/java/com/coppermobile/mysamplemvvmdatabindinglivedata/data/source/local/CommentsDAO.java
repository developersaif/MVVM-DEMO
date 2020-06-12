package com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Comments;

import java.util.List;

@Dao
public interface CommentsDAO {

    @Insert
    void insertComments(Comments Comment);

    @Query("SELECT * FROM comments WHERE dish_id=:dishId ORDER BY comment_time DESC;")
    LiveData<List<Comments>> getComments(int dishId);

    @Delete
    void deleteComments(Comments comments);
}