package com.example.explainme.contractMVP;

import com.example.explainme.data.network.model.DefinitionDto;
import com.example.explainme.data.network.model.SynonymDto;

public interface ExplainContract {
    interface View {

        void updateDefinitionView();
        void updateSynonymView();
        void setDefinition(DefinitionDto definition);
        void setSynonym(SynonymDto synonym);
        void showServerConnectionErrorDialog();
        void showProgressBar();
        void hideProgressBar();
        void showToast(CharSequence message, int duration);
        void getWord();
    }

    interface Presenter {
        void getDefinition(String word);
        void getSynonym(String word);
    }
}
