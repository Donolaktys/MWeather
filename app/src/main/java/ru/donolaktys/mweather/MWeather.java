package ru.donolaktys.mweather;

import android.app.Application;

import androidx.room.Room;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.donolaktys.mweather.data.DatabaseHistory;
import ru.donolaktys.mweather.interfaces.Constants;
import ru.donolaktys.mweather.interfaces.HistoryDao;

public class MWeather extends Application {
    private static Retrofit retrofit;
    private static MWeather instance;

    private DatabaseHistory dbf;

    public static MWeather getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildDB();
        buildRetrofit();
    }

    private void buildRetrofit() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    private void buildDB() {
        dbf = Room.databaseBuilder(
                getApplicationContext(),
                DatabaseHistory.class,
                "favorite_database")
                .allowMainThreadQueries() //Только для примеров и тестирования.
                .build();
    }

    public HistoryDao getHistoryDao() {
        return dbf.getHistoryDao();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
