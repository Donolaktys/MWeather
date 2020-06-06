package ru.donolaktys.mweather.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.donolaktys.mweather.interfaces.FavoriteDao;

@Database(entities = {FavoriteCity.class}, version = 1)
public abstract class DatabaseFavorite extends RoomDatabase {
    public abstract FavoriteDao getFavoriteDao();
}