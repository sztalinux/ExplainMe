package com.example.explainme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.explainme.R;
import com.example.explainme.data.network.model.Definition;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DefinitionRecyclerViewAdapter extends RecyclerView.Adapter<DefinitionRecyclerViewAdapter.DefinitionViewHolder> {

    private List<Definition> definitions;

    public DefinitionRecyclerViewAdapter(List<Definition> definitions) {
        this.definitions = definitions;
    }

    static class DefinitionViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.textview_definitionnumber)
        public TextView definitionNumberTextView;
        @BindView(R.id.textview_definitionpartofspeech)
        public TextView definitionPartOfSpeechTextView;
        @BindView(R.id.textview_definitioncontent)
        public TextView definitionContentTextView;

        DefinitionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_definition, parent, false);
        return new DefinitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.definitionNumberTextView.setText(position + 1);
        holder.definitionPartOfSpeechTextView.setText(definitions.get(position).getPartOfSpeech());
        holder.definitionContentTextView.setText(definitions.get(position).getDefinitionContent());
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }
}
