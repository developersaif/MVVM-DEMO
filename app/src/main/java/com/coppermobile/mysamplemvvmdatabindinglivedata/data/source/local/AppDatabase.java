package com.coppermobile.mysamplemvvmdatabindinglivedata.data.source.local;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Comments;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Dish;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Marvel;

@Database(entities = {Comments.class, Dish.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    private static final String COMMENT_DATABASE_NAME = "comments_db";

    public abstract CommentsDAO commentsDoaMethod();
    public abstract DishDAO dishDaoMethod();


    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            try {
                instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, COMMENT_DATABASE_NAME).build();
            } catch (Exception ignored) {
            }
        }
        return instance;
    }
}