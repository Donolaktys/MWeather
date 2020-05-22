package ru.donolaktys.mweather.data;

import android.util.ArrayMap;

public class SearchHistory {
    private ArrayMap<String, String> searchHistory;

    public ArrayMap<String, String> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(ArrayMap<String, String> searchHistory) {
        this.searchHistory = searchHistory;
    }
}
