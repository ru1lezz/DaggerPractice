package com.example.android.daggerpractice.injector.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.android.daggerpractice.data.db.WeatherDatabase;
import com.example.android.daggerpractice.data.network.WeatherService;
import com.example.android.daggerpractice.data.repository.SharedPrefRepositoryImpl;
import com.example.android.daggerpractice.data.repository.WeatherRepositoryImpl;
import com.example.android.daggerpractice.domain.SharedPrefRepository;
import com.example.android.daggerpractice.domain.WeatherRepository;
import com.example.android.daggerpractice.presentation.WeatherApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private final WeatherApplication weatherApplication;


    public ApplicationModule(WeatherApplication weatherApplication) {
        this.weatherApplication = weatherApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return weatherApplication;
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    SharedPrefRepository provideSharedPrefRepository(SharedPrefRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    WeatherDatabase provideWeatherDatabase(Context context) {
        return Room.databaseBuilder(context, WeatherDatabase.class, "weather_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    WeatherService provideWeatherService() {
        final String BASE_URL = "http://api.apixu.com/v1/";

        final Gson gson = new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(WeatherService.class);
    }
}

