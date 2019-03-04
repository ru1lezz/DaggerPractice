package com.example.android.daggerpractice.domain.interactor;

import com.example.android.daggerpractice.domain.WeatherRepository;
import com.example.android.daggerpractice.domain.model.Weather;

public class GetWeatherInteractor {
    private final String city;
    private final long epoch;
    private final WeatherRepository mRepository;

    public GetWeatherInteractor(String city, long epoch, WeatherRepository mRepository) {
        this.city = city;
        this.epoch = epoch;
        this.mRepository = mRepository;
    }

    public Weather execute() {
        return mRepository.getWeatherLocal(city, epoch);
    }
}
