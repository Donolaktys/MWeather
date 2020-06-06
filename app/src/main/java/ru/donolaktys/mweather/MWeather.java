package ru.donolaktys.mweather;

import android.app.Application;

import androidx.room.Room;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.donolaktys.mweather.data.DatabaseFavorite;
import ru.donolaktys.mweather.interfaces.Constants;
import ru.donolaktys.mweather.interfaces.FavoriteDao;

public class MWeather extends Application {
    private static Retrofit retrofit;
    private static MWeather instance;

    private DatabaseFavorite dbf;

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
                DatabaseFavorite.class,
                "favorite_database")
                .allowMainThreadQueries() //Только для примеров и тестирования.
                .build();
    }

    public FavoriteDao getFavoriteDao() {
        return dbf.getFavoriteDao();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
