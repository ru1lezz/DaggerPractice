package com.example.android.daggerpractice.presentation.injector.component;

import com.example.android.daggerpractice.presentation.injector.PerActivity;
import com.example.android.daggerpractice.presentation.injector.module.ActivityModule;
import com.example.android.daggerpractice.presentation.injector.module.SharefPrefModule;
import com.example.android.daggerpractice.presentation.injector.module.WeatherModule;
import com.example.android.daggerpractice.presentation.view.detailedweather.DetailedWeatherActivity;
import com.example.android.daggerpractice.presentation.view.weatherlist.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, WeatherModule.class, SharefPrefModule.class})
public interface WeatherComponent {
    void inject(MainActivity mainActivity);

    void inject(DetailedWeatherActivity detailedWeatherActivity);
}
