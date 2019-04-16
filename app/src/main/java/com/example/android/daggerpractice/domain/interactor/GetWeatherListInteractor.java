package com.example.android.daggerpractice.domain.interactor;

import com.example.android.daggerpractice.domain.WeatherRepository;
import com.example.android.daggerpractice.domain.model.DomainWeather;

import java.util.List;

import javax.inject.Inject;

public class GetWeatherListInteractor {
    private String city;
    private String days;
    private final WeatherRepository mRepository;

    @Inject
    public GetWeatherListInteractor(WeatherRepository repository) {
        mRepository = repository;
    }

    public List<DomainWeather> execute() {
        return mRepository.getRemoteWeatherList(city, days);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
