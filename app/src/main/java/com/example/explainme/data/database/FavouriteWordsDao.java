package com.example.explainme.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavouriteWordsDao {
    @Query("SELECT * FROM favouriteWords")
    List<FavouriteWordsEntity> getAll();

    @Insert(entity = FavouriteWordsEntity.class)
    void insertWord(String word);

//    @Delete
//    void deleteWord(FavouriteWordsEntity word);
}