package com.example.explainme.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.explainme.utils.Const;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;
import static com.example.explainme.utils.Const.PREFERENCES_KEY_HISTORY;

public class PreferencesManagerImpl implements PreferencesManager {

    private final SharedPreferences sharedPreferences;
    private Gson gson;

    public PreferencesManagerImpl(Context c) {
        sharedPreferences = c.getSharedPreferences(Const.PREFERENCES_FILE_NAME, MODE_PRIVATE);
        gson = new Gson();
    }


    @Override
    public void addWordToHistory(String word) {
        Set<String> words = new LinkedHashSet<>();
        Set<String> currentWords = new LinkedHashSet<>(sharedPreferences.getStringSet(PREFERENCES_KEY_HISTORY, new LinkedHashSet<>()));
        words.add(word);
        words.addAll(currentWords);
        sharedPreferences.edit().putStringSet(PREFERENCES_KEY_HISTORY, words).apply();
    }

    @Override
    public List<String> getHistoryWords() {
        return new ArrayList<>(sharedPreferences.getStringSet(PREFERENCES_KEY_HISTORY, new LinkedHashSet<>()));
    }

    @Override
    public void removeWordFromHistory(String word) {
        Set<String> words = new LinkedHashSet<>();
        Set<String> currentWords = new LinkedHashSet<>(sharedPreferences.getStringSet(PREFERENCES_KEY_HISTORY, new LinkedHashSet<>()));
        words.addAll(currentWords);
        words.remove(word);
    }


}
