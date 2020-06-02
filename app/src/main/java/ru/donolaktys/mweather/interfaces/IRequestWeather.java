package ru.donolaktys.mweather.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.donolaktys.mweather.data.WeatherRequest;

public interface IRequestWeather {
    @GET("weather")
    Call<WeatherRequest> loadWeather(@Query("q") String city, @Query("units") String units, @Query("appid") String keyApi);
}
