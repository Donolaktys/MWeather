package ru.donolaktys.mweather.interfaces;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import ru.donolaktys.mweather.data.FavoriteCity;

@Dao
public interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFavoriteCity(FavoriteCity favoriteCity);

    @Update
    void update(FavoriteCity favoriteCity);

    @Delete
    void delete(FavoriteCity favoriteCity);

    @Query("SELECT COUNT() FROM FAVORITECITY")
    int getCount();

    @Query("DELETE FROM FAVORITECITY")
    void deleteAll();

    @Query("SELECT ID, CITY, IMAGE, TEMPERATURE FROM FAVORITECITY")
    Cursor getFavorites();

    @Query("SELECT ID, CITY, IMAGE, TEMPERATURE FROM FAVORITECITY WHERE CITY=:city")
    FavoriteCity getFavoriteByCity(String city);

    @Query("SELECT ID, CITY, IMAGE, TEMPERATURE FROM FAVORITECITY LIMIT 1 OFFSET :position")
    FavoriteCity getFavoriteByID(int position);

    @Query("SELECT ID, CITY, IMAGE, TEMPERATURE FROM FAVORITECITY WHERE ID=:id")
    FavoriteCity getFavoriteById(long id);
}
