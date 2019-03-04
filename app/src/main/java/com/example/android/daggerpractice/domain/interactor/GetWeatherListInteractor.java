package com.example.android.daggerpractice.domain.interactor;

import com.example.android.daggerpractice.domain.WeatherRepository;
import com.example.android.daggerpractice.domain.model.Weather;

import java.util.List;

public class GetWeatherListInteractor {
    private final String city;
    private final String days;
    private final WeatherRepository mRepository;

    public GetWeatherListInteractor(String city, String days, WeatherRepository mRepository) {
        this.city = city;
        this.days = days;
        this.mRepository = mRepository;
    }

    public List<Weather> execute() {
        return mRepository.getRemoteWeatherList(city, days);
    }
}
