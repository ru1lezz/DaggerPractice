package com.example.android.daggerpractice.presentation.view.weatherlist;

import com.example.android.daggerpractice.presentation.view.model.Weather;

import java.util.List;

public interface MainView {
    void displayWeatherList(List<Weather> weatherList);
    void setCity(String city);
    void setDays(int position);
}