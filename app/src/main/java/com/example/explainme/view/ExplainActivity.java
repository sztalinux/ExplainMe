package com.example.explainme.view;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.explainme.R;
import com.example.explainme.contractMVP.ExplainContract;
import com.example.explainme.presenter.ExplainPresenter;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Objects;

@EActivity(R.layout.activity_explain)
public class ExplainActivity extends AppCompatActivity implements ExplainContract.View {

    ExplainPresenter presenter;

    @ViewById(R.id.editText_wordtoexplain)
    EditText wordEditText;

    @Click(R.id.imageButton_searchword)
    void searchWord(){
        String word = Objects.requireNonNull(wordEditText.getText().toString());
        getWordDefinition(word);
    }

    private void getWordDefinition(String word) {
        presenter.getDefinition(word);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ExplainPresenter();
        presenter.attach(this);
    }

    @Override
    public void updateView() {

    }
}
