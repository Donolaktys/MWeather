package ru.donolaktys.mweather.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.donolaktys.mweather.interfaces.HistoryDao;

@Database(entities = {History.class}, version = 1)
public abstract class DatabaseHistory extends RoomDatabase {
    public abstract HistoryDao getHistoryDao();
}
