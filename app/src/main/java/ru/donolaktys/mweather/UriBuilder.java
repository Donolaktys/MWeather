package ru.donolaktys.mweather;

import ru.donolaktys.mweather.interfaces.Constants;

public class UriBuilder implements Constants {
    String requestUri;

    public UriBuilder(String city) {
        requestUri = WEATHER_URL + city;
        requestUri += "&units=metric";
        requestUri += "&APPID=" + BuildConfig.WEATHER_API_KEY;
    }

    public String getRequestUri() {
        return requestUri;
    }
}
