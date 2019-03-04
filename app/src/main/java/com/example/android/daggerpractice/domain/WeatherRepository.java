package com.example.android.daggerpractice.domain;

import com.example.android.daggerpractice.domain.model.Weather;

import java.util.List;

public interface WeatherRepository {
    List<Weather> getRemoteWeatherList(String city, String days);
    Weather getWeatherLocal(String city, long epoch);
}
