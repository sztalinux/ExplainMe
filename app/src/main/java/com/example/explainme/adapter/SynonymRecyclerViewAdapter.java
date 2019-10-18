package com.example.explainme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.explainme.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SynonymRecyclerViewAdapter extends RecyclerView.Adapter<SynonymRecyclerViewAdapter.SynonymViewHolder>  {

    private List<String> synonyms;

    public SynonymRecyclerViewAdapter(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    static class SynonymViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.textview_synonymnumber)
        public TextView synonymNumberTextView;
        @BindView(R.id.textview_synonymcontent)
        public TextView synonymContentTextView;

        SynonymViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public SynonymViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_synonym, parent, false);
        return new SynonymRecyclerViewAdapter.SynonymViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SynonymRecyclerViewAdapter.SynonymViewHolder holder, int position) {
        holder.synonymNumberTextView.setText(position + 1);
        holder.synonymContentTextView.setText(synonyms.get(position));
    }

    @Override
    public int getItemCount() {
        return synonyms.size();
    }
}
