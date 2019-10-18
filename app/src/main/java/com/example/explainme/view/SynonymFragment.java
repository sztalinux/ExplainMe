package com.example.explainme.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.explainme.R;
import com.example.explainme.adapter.DefinitionRecyclerViewAdapter;
import com.example.explainme.adapter.SynonymRecyclerViewAdapter;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.explainme.utils.Const.SYNONYM_INTENT;

@EFragment(R.layout.fragment_synonym_list)
public class SynonymFragment extends Fragment {

    private RecyclerView.Adapter adapter;
    private List<String> synonyms;
    @BindView(R.id.recyclerview_definition)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_definition_list, container, false);
        ButterKnife.bind(this, view);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                synonymReceiver, new IntentFilter(SYNONYM_INTENT));
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        setRecyclerView();
    }

    private BroadcastReceiver synonymReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setDefinitions();
            adapter.notifyDataSetChanged();
        }
    };

    private void setDefinitions() {
        synonyms.clear();
        synonyms = ((ExplainActivity) getActivity()).getSynonym().getSynonyms();
    }

    private void initializeDefinitions(){
        synonyms = new ArrayList<>();
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initializeDefinitions();
        adapter = new SynonymRecyclerViewAdapter(synonyms);
        recyclerView.setAdapter(adapter);
    }
}

