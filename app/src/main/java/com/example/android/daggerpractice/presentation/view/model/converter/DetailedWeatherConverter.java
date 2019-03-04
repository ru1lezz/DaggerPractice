package com.example.android.daggerpractice.presentation.view.model.converter;

import com.example.android.daggerpractice.domain.Converter;
import com.example.android.daggerpractice.domain.model.Weather;
import com.example.android.daggerpractice.presentation.view.model.DetailedWeatherUIModel;

public class DetailedWeatherConverter extends Converter<Weather, DetailedWeatherUIModel> {
    @Override
    public DetailedWeatherUIModel convertTo(Weather weather) {
        DetailedWeatherUIModel to = new DetailedWeatherUIModel();
        to.setAvgHumidity(weather.getAvgHumidity());
        to.setDate(weather.getDate());
        to.setEpoch(weather.getEpoch());
        to.setMaxTemp(weather.getMaxTemp());
        to.setMaxWind(weather.getMaxWind());
        to.setMinTemp(weather.getMinTemp());
        to.setText(weather.getText());
        to.setUrlIcon(weather.getIconUrl());
        return to;
    }

    @Override
    public Weather convertFrom(DetailedWeatherUIModel detailedWeatherUIModel) {
        return null;
    }
}
