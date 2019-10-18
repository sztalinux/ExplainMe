package com.example.explainme.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
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
import com.example.explainme.data.network.model.Definition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.explainme.utils.Const.DEFINITION_INTENT;

public class DefinitionFragment extends Fragment {

    private final String TAG = "DefinitionFragment";

    private RecyclerView.Adapter adapter;
    private List<Definition> definitions;
    @BindView(R.id.recyclerview_definition)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_definition_list, container, false);
        ButterKnife.bind(this, view);
        Log.e(TAG, "onCreateView: ");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                definitionReceiver, new IntentFilter(DEFINITION_INTENT));
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.e(TAG, "onStart: ");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                definitionReceiver, new IntentFilter(DEFINITION_INTENT));
        setRecyclerView();
    }

    private BroadcastReceiver definitionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "onReceive: ");
            setDefinitions();
            adapter.notifyDataSetChanged();
        }
    };

    private void setDefinitions() {
        definitions.clear();
        definitions = ((ExplainActivity) getActivity()).getDefinition().getDefinitions();
    }

    private void initializeDefinitions(){
        definitions = new ArrayList<>();
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initializeDefinitions();
        adapter = new DefinitionRecyclerViewAdapter(definitions);
        recyclerView.setAdapter(adapter);
    }
}
