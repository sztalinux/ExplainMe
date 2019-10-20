package com.example.explainme.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.explainme.R;
import com.example.explainme.adapter.DefinitionRecyclerViewAdapter;
import com.example.explainme.adapter.HistoryRecyclerViewAdapter;
import com.example.explainme.data.prefs.PreferencesManager;
import com.example.explainme.data.prefs.PreferencesManagerImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    private final String TAG = "HistoryActivity";
    private List<String> historyWords;
    private RecyclerView.Adapter adapter;
    private PreferencesManager preferencesManager;

    @BindView(R.id.recyclerview_history)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        preferencesManager = new PreferencesManagerImpl(this);
        setRecyclerView();
        updateView();
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initializeHistory();
        adapter = new HistoryRecyclerViewAdapter(historyWords, this::deleteHistoryWord);
        recyclerView.setAdapter(adapter);
    }

    private void initializeHistory() {
        historyWords = new ArrayList<>();
    }

    public void deleteHistoryWord(String word){
        preferencesManager.removeWordFromHistory(word);
    }

    private void updateView() {
        historyWords.clear();
        historyWords.addAll(preferencesManager.getHistoryWords());
        Log.e(TAG, historyWords.get(0));
        adapter.notifyDataSetChanged();
    }
}
