package com.example.android.daggerpractice.presentation.injector.module;

import com.example.android.daggerpractice.domain.interactor.GetWeatherInteractor;
import com.example.android.daggerpractice.domain.interactor.GetWeatherListInteractor;
import com.example.android.daggerpractice.presentation.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherModule {

    @Provides
    @PerActivity
    GetWeatherListInteractor provideGetWeatherListInteractor(GetWeatherListInteractor getWeatherListInteractor) {
        return getWeatherListInteractor;
    }

    @Provides
    @PerActivity
    GetWeatherInteractor provideGetWeatherInteractor(GetWeatherInteractor getWeatherInteractor) {
        return getWeatherInteractor;
    }
}
