package com.example.explainme.presenter;

import android.util.Log;

import com.example.explainme.contractMVP.ExplainContract;
import com.example.explainme.data.network.ApiManager;
import com.example.explainme.data.network.ApiManagerImpl;
import com.example.explainme.data.network.model.DefinitionDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExplainPresenter extends BasePresenter<ExplainContract.View> implements ExplainContract.Presenter {

    private final String TAG = "ExplainPresenter";
    ApiManager apiManager;

    public ExplainPresenter() {
        this.apiManager = new ApiManagerImpl();
    }


    @Override
    public void getDefinition(String word) {
        apiManager.getDefinition(new Callback<DefinitionDto>() {

            @Override
            public void onResponse(Call<DefinitionDto> call, Response<DefinitionDto> response) {
                if(response.body() == null) {
                    Log.e(TAG, "onResponse: nullObject");
                } else {
                    Log.e(TAG, "onResponse: " + response.body().getWord() + "\t" + response.body().getDefinitions().get(0));
                    view.updateView();
                }
            }

            @Override
            public void onFailure(Call<DefinitionDto> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        }, word);
    }
}
