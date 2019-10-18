package com.example.explainme.data.prefs;

import com.example.explainme.data.network.model.SynonymDto;

public interface PreferencesManager {

    void setSynonyms(SynonymDto synonym);

    SynonymDto getSynonyms();
}
