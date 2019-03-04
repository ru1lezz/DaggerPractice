package com.example.android.daggerpractice.presentation.view.detailedweather;

public interface DetailedWeatherView {
    void setCity(String city);

    void setDate(String date);

    void setWeatherDescription(String description);

    void setHumidity(String humidity);

    void setHighTemperature(String highTemperature);

    void setLowTemperature(String lowTemperature);

    void setWind(String wind);

    void setIconUrl(String url);
}
