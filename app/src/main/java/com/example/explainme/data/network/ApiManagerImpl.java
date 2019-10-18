package com.example.explainme.data.network;

import com.example.explainme.data.network.model.DefinitionDto;
import com.example.explainme.data.network.model.SynonymDto;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiManagerImpl implements ApiManager {
    @Override
    public void getDefinition(Callback<DefinitionDto> callback, String word) {
        ApiService apiService = ApiClient.getInstance().getApiService();
        Call<DefinitionDto> call = apiService.getDefinitions(word);
        call.enqueue(callback);
    }

    @Override
    public void getSynonym(Callback<SynonymDto> callback, String word){
        ApiService apiService = ApiClient.getInstance().getApiService();
        Call<SynonymDto> call = apiService.getSynonyms(word);
        call.enqueue(callback);
    }
}
