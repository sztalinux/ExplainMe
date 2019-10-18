package com.example.explainme.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.explainme.data.network.model.SynonymDto;
import com.example.explainme.utils.Const;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesManagerImpl implements PreferencesManager {

    private final SharedPreferences apiResponseSharedPreferences;
    private final SharedPreferences searchHistorySharedPreferences;
    private Gson gson;

    public PreferencesManagerImpl(Context c) {
        apiResponseSharedPreferences = c.getSharedPreferences(Const.PREFERENCES_FILE_NAME_API_RESPONSES, MODE_PRIVATE);
        searchHistorySharedPreferences = c.getSharedPreferences(Const.PREFERENCES_FILE_NAME_SEARCH_HISTORY, MODE_PRIVATE);
        gson = new Gson();
    }

    @Override
    public void setSynonyms(SynonymDto synonym) {
        apiResponseSharedPreferences.edit().putString(Const.PREFERENCES_KEY_SYNONYMS, gson.toJson(synonym)).apply();
    }

    @Override
    public SynonymDto getSynonyms() {
        String synonymsJson = apiResponseSharedPreferences.getString(Const.PREFERENCES_KEY_SYNONYMS, "");
        return gson.fromJson(synonymsJson, SynonymDto.class);
    }
}
