package com.example.android.daggerpractice.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherList {
    @SerializedName("forecastday")
    private List<RemoteWeather> items;

    public List<RemoteWeather> getItems() {
        return items;
    }

    public void setItems(List<RemoteWeather> items) {
        this.items = items;
    }
}
