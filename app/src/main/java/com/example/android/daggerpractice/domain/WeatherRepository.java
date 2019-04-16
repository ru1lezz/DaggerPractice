package com.example.android.daggerpractice.domain;

import com.example.android.daggerpractice.domain.model.DomainWeather;

import java.util.List;

public interface WeatherRepository {
    List<DomainWeather> getRemoteWeatherList(String city, String days);
    DomainWeather getWeatherLocal(String city, long epoch);
}
