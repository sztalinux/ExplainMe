package com.example.explainme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.explainme.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.WifiViewHolder> {

    private List<String> words;
    private RecyclerViewClickListener onClickListener;

    public HistoryRecyclerViewAdapter(List<String> words, RecyclerViewClickListener onClickListener) {
        this.words = words;
        this.onClickListener = onClickListener;
    }

    class WifiViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_history_word)
        public TextView wordTextView;
        @BindView(R.id.imagebutton_history_worddelete)
        public ImageButton wordDeleteButton;

        WifiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            wordDeleteButton.setOnClickListener(view -> onClickListener.onDeleteButtonClick(wordTextView.getText().toString()));
        }

    }

    @NonNull
    @Override
    public WifiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_word, parent, false);
        return new WifiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WifiViewHolder holder, int position) {
        holder.wordTextView.setText(words.get(position));
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public interface RecyclerViewClickListener {

        void onDeleteButtonClick(String word);
    }
}
