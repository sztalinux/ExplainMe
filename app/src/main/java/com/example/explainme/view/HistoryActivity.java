package com.example.explainme.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.explainme.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
    }

}
