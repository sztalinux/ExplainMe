package com.example.explainme.data.prefs;

import com.example.explainme.data.network.model.SynonymDto;

import java.util.List;
import java.util.Set;

public interface PreferencesManager {

    void addWordToHistory(String word);

    List<String> getHistoryWords();

    void removeWordFromHistory(String word);
}
