package ru.donolaktys.mweather.data;

import java.util.ArrayList;

public class SearchHistory {
    private static ArrayList<String> searchHistory;

    public static ArrayList<String> getSearchHistory() {
        if (searchHistory == null) {
            searchHistory = new ArrayList<>();
        }
        return searchHistory;
    }
}
