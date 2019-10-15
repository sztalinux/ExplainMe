package com.example.explainme.data.network;

import com.example.explainme.data.network.model.DefinitionDto;

import retrofit2.Callback;

public interface ApiManager {
    void getDefinition(Callback<DefinitionDto> callback, String word);
}
