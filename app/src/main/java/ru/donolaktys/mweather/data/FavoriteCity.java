package ru.donolaktys.mweather.data;

import android.util.ArrayMap;

import java.util.ArrayList;

public class FavoriteCity {
    private static ArrayList<String[]> favorite;

    public void add(String[] favoriteList) {
        favorite.add(favoriteList);
    }

    public static ArrayList<String[]> getFavoriteCity() {
        if (favorite == null) {
            favorite = new ArrayList<>();
        }
        return favorite;
    }
}
