package com.example.explainme.data.network;

import com.example.explainme.data.network.model.DefinitionDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.example.explainme.utils.Const.API_KEY;

public interface ApiService {

    @Headers({"x-rapidapi-key: 3fc2283c4emsh83751c04b567a82p1246bfjsnc69dc8712c4d"})
    @GET("words/{word}/definitions/")
    Call<DefinitionDto> getDefinitions(@Path("word") String word);
}
