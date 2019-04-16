package com.example.android.daggerpractice.domain.interactor;

import com.example.android.daggerpractice.domain.WeatherRepository;
import com.example.android.daggerpractice.domain.model.DomainWeather;

import javax.inject.Inject;

public class GetWeatherInteractor {
    private String city;
    private long epoch;
    private final WeatherRepository mRepository;

    @Inject
    public GetWeatherInteractor(WeatherRepository repository) {
        mRepository = repository;
    }

    public DomainWeather execute() {
        return mRepository.getWeatherLocal(city, epoch);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }
}
