package com.example.explainme.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.example.explainme.R;
import com.example.explainme.adapter.ExplainTabsAdapter;
import com.example.explainme.contractMVP.ExplainContract;
import com.example.explainme.data.database.AppDatabase;
import com.example.explainme.data.network.model.DefinitionDto;
import com.example.explainme.data.network.model.SynonymDto;
import com.example.explainme.data.prefs.PreferencesManager;
import com.example.explainme.data.prefs.PreferencesManagerImpl;
import com.example.explainme.presenter.ExplainPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Getter;

import static com.example.explainme.utils.Const.DATABASE_NAME;
import static com.example.explainme.utils.Const.DEFINITION_INTENT;
import static com.example.explainme.utils.Const.SYNONYM_INTENT;


public class ExplainActivity extends AppCompatActivity implements ExplainContract.View, ServerConnectionErrorDialogFragment.ServerConnectionErrorDialogListener {

    @Getter
    private DefinitionDto definition;
    @Getter
    private SynonymDto synonym;

    ExplainPresenter presenter;
    ExplainTabsAdapter tabsAdapter;
    private PreferencesManager preferencesManager;
    private String wordToSearch;

    @BindView(R.id.editText_wordtoexplain)
    EditText wordEditText;
    @BindView(R.id.textView_searchedWord)
    TextView searchedWordTextView;
    @BindView(R.id.viewpager_result)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.constraintLayout_result)
    ConstraintLayout resultLayout;
    @BindView(R.id.progressbar_explain_loading)
    ProgressBar progressBar;

    @OnClick(R.id.imageButton_searchword)
    void searchWord(){
        wordToSearch = Objects.requireNonNull(wordEditText.getText().toString());
        preferencesManager.addWordToHistory(wordToSearch);
        wordEditText.getText().clear();
        getWord();
    }

    @OnClick(R.id.imageButton_addToFavourites)
    void addToFavourites(){
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();
        db.favouriteWordsDao().insertWord(searchedWordTextView.getText().toString());
        showToast("Added to Favourites", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        ButterKnife.bind(this);
        preferencesManager = new PreferencesManagerImpl(this);
        presenter = new ExplainPresenter();
        presenter.attach(this);
        definition = new DefinitionDto("", new ArrayList<>());
        synonym = new SynonymDto("", new ArrayList<>());

        hideProgressBar();
        setupAdapter();
        viewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(viewPager);
        if (savedInstanceState != null){
            if(!TextUtils.isEmpty(savedInstanceState.getString("word"))){
                wordToSearch = savedInstanceState.getString("word");
                getWord();
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.setTransactionSafe(true);
        presenter.runPendingTransactions();
    }

    protected void onSaveInstanceState(Bundle icicle) {
        super.onSaveInstanceState(icicle);
        icicle.putString("word", wordToSearch);
    }

    public void onPostResume() {
        super.onPostResume();
        presenter.setTransactionSafe(true);
        presenter.runPendingTransactions();
    }

    public void onPause() {
        super.onPause();
        presenter.setTransactionSafe(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.setTransactionSafe(false);
    }

    @Override
    public void showToast(CharSequence message, int duration) {
        Toast.makeText(this, message, duration).show();
    }

    @Override
    public void updateDefinitionView() {
        Intent definitionIntent = new Intent(DEFINITION_INTENT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(definitionIntent);
        searchedWordTextView.setText(definition.getWord());
        resultLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateSynonymView() {
        Intent synonymIntent = new Intent(SYNONYM_INTENT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(synonymIntent);
        resultLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDefinition(DefinitionDto definition) {
        this.definition.setWord(definition.getWord());
        this.definition.getDefinitions().clear();
        this.definition.getDefinitions().addAll(definition.getDefinitions());
    }

    @Override
    public void setSynonym(SynonymDto synonym) {
        this.synonym.setWord(synonym.getWord());
        this.synonym.getSynonyms().clear();
        this.synonym.getSynonyms().addAll(synonym.getSynonyms());
    }

    @Override
    public void showServerConnectionErrorDialog() {
        DialogFragment dialog = new ServerConnectionErrorDialogFragment();
        dialog.show(getSupportFragmentManager(), "ServerConnectionErrorDialogFragment");

    }

    @Override
    public void onServerConnectionErrorDialogPositiveClick(DialogFragment dialog) {
        searchWord();
    }

    @Override
    public void onServerConnectionErrorDialogNegativeClick(DialogFragment dialog) {

    }

    void setupAdapter(){
        tabsAdapter = new ExplainTabsAdapter(getSupportFragmentManager());
        tabsAdapter.addFragment(new DefinitionFragment(), "Definition");
        tabsAdapter.addFragment(new SynonymFragment(), "Synonym");
    }

    public void getWord(){
        showProgressBar();
        presenter.getDefinition(wordToSearch);
        presenter.getSynonym(wordToSearch);
    }
}
