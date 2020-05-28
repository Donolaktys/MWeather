package ru.donolaktys.mweather;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import ru.donolaktys.mweather.interfaces.Constants;

public class Settings extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
    }

    private void setTheme() {
        if (isDarkTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    protected boolean isDarkTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_PREFERENCE_THEME, MODE_PRIVATE);

        return sharedPreferences.getBoolean(IS_DARK_THEME, true);
    }

    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_PREFERENCE_THEME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_DARK_THEME, isDarkTheme);
        editor.apply();
    }
}
