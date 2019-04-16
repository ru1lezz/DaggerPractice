package com.example.android.daggerpractice.domain.interactor;

import com.example.android.daggerpractice.domain.WeatherRepository;
import com.example.android.daggerpractice.domain.model.DomainWeather;

import java.util.List;

import io.reactivex.Observable;

public class GetWeatherListReactiveInteractor {
    private final String city;
    private final String days;
    private final WeatherRepository mRepository;

    public GetWeatherListReactiveInteractor(String city, String days, WeatherRepository mRepository) {
        this.city = city;
        this.days = days;
        this.mRepository = mRepository;
    }

    public Observable<List<DomainWeather>> execute() {
        return mRepository.getWeatherListReactive(city, days);
    }
}
