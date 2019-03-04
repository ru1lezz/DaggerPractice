package com.example.android.daggerpractice.data.network;

import com.example.android.daggerpractice.data.WeatherDto;
import com.example.android.daggerpractice.data.network.model.Weather;
import com.example.android.daggerpractice.domain.Converter;

public class DtoConverter extends Converter<Weather, WeatherDto> {
    private final String city;

    public DtoConverter(String city) {
        this.city = city;
    }

    @Override
    public WeatherDto convertTo(Weather from) {
        WeatherDto to = new WeatherDto();
        to.setCity(city);
        to.setEpoch(from.getEpoch());
        to.setDate(from.getDate());
        to.setAvgHumidity(from.getDay().getAvgHumidity());
        to.setIconUrl(from.getDay().getCondition().getIconUrl());
        to.setMaxTemp(from.getDay().getMaxTemp());
        to.setMinTemp(from.getDay().getMinTemp());
        to.setMaxWind(from.getDay().getMaxWind());
        to.setText(from.getDay().getCondition().getText());
        return to;
    }

    @Override
    public Weather convertFrom(WeatherDto to) {
        return null;
    }
}