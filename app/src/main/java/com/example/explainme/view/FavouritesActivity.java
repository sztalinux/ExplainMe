package com.example.explainme.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.explainme.R;
import com.example.explainme.adapter.WordsListRecyclerViewAdapter;
import com.example.explainme.data.database.AppDatabase;
import com.example.explainme.data.database.FavouriteWordsEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.explainme.utils.Const.DATABASE_NAME;


public class FavouritesActivity extends AppCompatActivity {

    private final String TAG = "FavouriteActivity";
    private RecyclerView.Adapter adapter;
    private List<String> favouriteWords;
    private AppDatabase database;

    @BindView(R.id.recyclerview_favourites)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ButterKnife.bind(this);
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();
        List<FavouriteWordsEntity> words;
        words = database.favouriteWordsDao().getAll();
        Log.e(TAG, words.get(0).getFavouriteWord());
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initializeHistory();
        adapter = new WordsListRecyclerViewAdapter(favouriteWords, this::deleteHistoryWord);
        recyclerView.setAdapter(adapter);
    }

    private void initializeHistory() {
        favouriteWords = new ArrayList<>();
    }

    public void deleteHistoryWord(String word){
//        database.favouriteWordsDao().deleteWord(word);
    }

    private void updateView() {
        favouriteWords.clear();
        List<FavouriteWordsEntity> words = database.favouriteWordsDao().getAll();
        for (FavouriteWordsEntity word:words) {
            favouriteWords.add(word.favouriteWord);
        }
        adapter.notifyDataSetChanged();
    }

}
