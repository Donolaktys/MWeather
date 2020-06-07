package ru.donolaktys.mweather.interfaces;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import ru.donolaktys.mweather.data.History;

@Dao
public interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertHistory(History history);

    @Update
    void update(History history);

    @Delete
    void delete(History history);

    @Query("SELECT COUNT() FROM HISTORY")
    int getCount();

    @Query("DELETE FROM HISTORY")
    void deleteAll();

    @Query("SELECT ID, CITY, IMAGE, TEMPERATURE FROM HISTORY")
    Cursor getHistories();

    @Query("SELECT ID, CITY, IMAGE, TEMPERATURE FROM History WHERE ID=:id")
    History getHistoryById(long id);

    @Query("SELECT ID, CITY, IMAGE, TEMPERATURE FROM History LIMIT 1 OFFSET :position")
    History getHistory(int position);
}
