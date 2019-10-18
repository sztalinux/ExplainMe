package com.example.explainme.data.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;

@Getter
@Entity(tableName = "favouriteWords")
public class FavouriteWords {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "word")
    public String word;
}