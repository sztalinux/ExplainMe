package com.example.explainme.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.explainme.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Click(R.id.button_explain)
    public void goToExplainActivity() {
        Intent intent = new Intent(this, ExplainActivity_.class);
        this.startActivity(intent);
    }

    @Click(R.id.button_favourites)
    public void goToFavouritesActivity() {
        Intent intent = new Intent(this, FavouritesActivity_.class);
        this.startActivity(intent);
    }

    @Click(R.id.button_history)
    public void goToHistoryActivity() {
        Intent intent = new Intent(this, HistoryActivity_.class);
        this.startActivity(intent);
    }
}
