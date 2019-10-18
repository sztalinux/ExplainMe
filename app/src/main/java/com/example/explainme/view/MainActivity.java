package com.example.explainme.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.explainme.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_explain)
    public void goToExplainActivity() {
        Intent intent = new Intent(this, ExplainActivity.class);
        this.startActivity(intent);
    }

    @OnClick(R.id.button_favourites)
    public void goToFavouritesActivity() {
        Intent intent = new Intent(this, FavouritesActivity.class);
        this.startActivity(intent);
    }

    @OnClick(R.id.button_history)
    public void goToHistoryActivity() {
        Intent intent = new Intent(this, HistoryActivity.class);
        this.startActivity(intent);
    }
}
