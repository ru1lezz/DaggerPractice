package com.example.android.daggerpractice.presentation;

import android.app.Application;

import com.example.android.daggerpractice.presentation.injector.component.ApplicationComponent;
import com.example.android.daggerpractice.presentation.injector.component.DaggerApplicationComponent;
import com.example.android.daggerpractice.presentation.injector.module.ApplicationModule;

public class WeatherApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() { return applicationComponent; }
}
