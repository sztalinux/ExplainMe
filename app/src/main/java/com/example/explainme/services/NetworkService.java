package com.example.explainme.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class NetworkService extends IntentService {

    public NetworkService() {
        super("NetworkService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
