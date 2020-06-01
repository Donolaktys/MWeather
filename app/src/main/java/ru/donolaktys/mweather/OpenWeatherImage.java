package ru.donolaktys.mweather;

import ru.donolaktys.mweather.interfaces.Constants;
import ru.donolaktys.mweather.interfaces.ImageUrlBuilder;

public class OpenWeatherImage implements ImageUrlBuilder, Constants {
    private String icon;
    private String url;

    public OpenWeatherImage(String icon) {
        this.icon = icon;
    }

    @Override
    public String build() {
        url = ICON_URL_START + icon + ICON_URL_END;
        return url;
    }
}
