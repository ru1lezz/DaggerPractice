package com.example.android.daggerpractice.presentation.view.model.converter;

import com.example.android.daggerpractice.domain.Converter;
import com.example.android.daggerpractice.domain.model.DomainWeather;
import com.example.android.daggerpractice.presentation.view.model.Weather;

public class WeatherUiModelConverter extends Converter<DomainWeather, Weather> {
    @Override
    public Weather convertTo(DomainWeather from) {
        Weather to = new Weather();
        to.setCity(from.getCity());
        to.setDate(from.getDate());
        to.setEpoch(from.getEpoch());
        to.setMaxTemp(from.getMaxTemp());
        to.setMinTemp(from.getMinTemp());
        to.setText(from.getText());
        to.setUrlIcon(from.getIconUrl());
        to.setAvgHumidity(from.getAvgHumidity());
        to.setMaxWind(from.getMaxWind());
        return to;
    }

    @Override
    public DomainWeather convertFrom(Weather weather) {
        return null;
    }
}