package com.example.explainme.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FavouriteWords.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavouriteWordsDao favouriteWordsDao();
}