package com.example.explainme.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.explainme.contractMVP.ExplainContract;
import com.example.explainme.data.network.ApiManager;
import com.example.explainme.data.network.ApiManagerImpl;
import com.example.explainme.data.network.model.DefinitionDto;
import com.example.explainme.data.network.model.SynonymDto;
import com.example.explainme.data.prefs.PreferencesManager;
import com.example.explainme.data.prefs.PreferencesManagerImpl;

import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExplainPresenter extends BasePresenter<ExplainContract.View> implements ExplainContract.Presenter {

    private final String TAG = "ExplainPresenter";
    ApiManager apiManager;

    @Setter
    private boolean isTransactionSafe;
    private boolean isTransactionPending;

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
                    view.hideProgressBar();
                    view.showToast("No such favouriteWord", Toast.LENGTH_LONG);
                } else {
                    Log.e(TAG, "onResponse: " + response.body().getWord() + "\t" + response.body().getDefinitions().get(0));
                    view.setDefinition(response.body());
                    view.updateDefinitionView();
                    view.hideProgressBar();
                }
            }

            @Override
            public void onFailure(Call<DefinitionDto> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                view.hideProgressBar();
                showServerConnectionErrorDialog();
            }
        }, word);
    }

    @Override
    public void getSynonym(String word) {
        apiManager.getSynonym(new Callback<SynonymDto>() {

            @Override
            public void onResponse(Call<SynonymDto> call, Response<SynonymDto> response) {
                if(response.body() == null) {
                    Log.e(TAG, "onResponse: nullObject");
                } else {
                    Log.e(TAG, "onResponse: " + response.body().getWord() + "\t" + response.body().getSynonyms().get(0));
                    view.setSynonym(response.body());
                    view.hideProgressBar();
                    view.updateDefinitionView();
                }
            }

            @Override
            public void onFailure(Call<SynonymDto> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                view.hideProgressBar();
                showServerConnectionErrorDialog();
            }
        }, word);
    }

    public void runPendingTransactions() {
        if (isTransactionPending) {
            showServerConnectionErrorDialog();
        }
    }

    private void showServerConnectionErrorDialog() {
        if (isTransactionSafe) {
            view.showServerConnectionErrorDialog();
            isTransactionPending = false;
        } else {
            isTransactionPending = true;
        }
    }
}
