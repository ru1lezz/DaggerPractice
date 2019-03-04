package com.example.android.daggerpractice.presentation.injector.component;

import android.support.v7.app.AppCompatActivity;

import com.example.android.daggerpractice.domain.SharedPrefRepository;
import com.example.android.daggerpractice.domain.WeatherRepository;
import com.example.android.daggerpractice.presentation.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject (AppCompatActivity appCompatActivity);

    WeatherRepository getForecastRepository();

    SharedPrefRepository getSharedPrefRepository();
}
