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

public class WordsListRecyclerViewAdapter extends RecyclerView.Adapter<WordsListRecyclerViewAdapter.WordViewHolder> {

    private List<String> words;
    private RecyclerViewClickListener onClickListener;

    public WordsListRecyclerViewAdapter(List<String> words, RecyclerViewClickListener onClickListener) {
        this.words = words;
        this.onClickListener = onClickListener;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_word)
        public TextView wordTextView;
        @BindView(R.id.imagebutton_worddelete)
        public ImageButton wordDeleteButton;

        WordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            wordDeleteButton.setOnClickListener(view -> onClickListener.onDeleteButtonClick(wordTextView.getText().toString()));
        }

    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
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
