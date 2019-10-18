package com.example.explainme.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.explainme.R;
import com.example.explainme.data.database.AppDatabase;
import com.example.explainme.data.database.FavouriteWords;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.util.List;

import butterknife.ButterKnife;

import static com.example.explainme.utils.Const.DATABASE_NAME;


public class FavouritesActivity extends AppCompatActivity {

    private final String TAG = "FavouriteActivity";
    private List<FavouriteWords> words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ButterKnife.bind(this);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();
        List<FavouriteWords> words;
        words = db.favouriteWordsDao().getAll();
        Log.e(TAG, words.get(0).getWord());
    }

}
