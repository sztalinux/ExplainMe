package com.example.explainme.contractMVP;

public interface ExplainContract {
    interface View {

        void updateView();
    }

    interface Presenter {
        void getDefinition(String word);
    }
}
