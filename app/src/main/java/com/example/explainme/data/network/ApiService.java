package com.example.explainme.data.network;

import com.example.explainme.data.network.model.DefinitionDto;
import com.example.explainme.data.network.model.SynonymDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.example.explainme.utils.Const.API_KEY;

public interface ApiService {

    @Headers({"x-rapidapi-key: 07c5da8bcemsh2809037da159b71p1d7145jsn76638ca8717d", "x-rapidapi-host: wordsapiv1.p.rapidapi.com"})
    @GET("words/{favouriteWord}/definitions/")
    Call<DefinitionDto> getDefinitions(@Path("favouriteWord") String word);

    @Headers({"x-rapidapi-key: 07c5da8bcemsh2809037da159b71p1d7145jsn76638ca8717d", "x-rapidapi-host: wordsapiv1.p.rapidapi.com"})
    @GET("words/{favouriteWord}/synonyms/")
    Call<SynonymDto> getSynonyms(@Path("favouriteWord") String word);
}
