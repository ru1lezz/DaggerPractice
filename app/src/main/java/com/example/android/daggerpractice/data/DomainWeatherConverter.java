package com.example.android.daggerpractice.data;

import com.example.android.daggerpractice.domain.Converter;
import com.example.android.daggerpractice.domain.model.Weather;

public class DomainWeatherConverter extends Converter<WeatherDto, Weather> {
    @Override
    public Weather convertTo(WeatherDto from) {
        Weather to = new Weather();
        to.setCity(from.getCity());
        to.setEpoch(from.getEpoch());
        to.setDate(from.getDate());
        to.setAvgHumidity(from.getAvgHumidity());
        to.setIconUrl(from.getIconUrl());
        to.setMaxTemp(from.getMaxTemp());
        to.setMinTemp(from.getMinTemp());
        to.setMaxWind(from.getMaxWind());
        to.setText(from.getText());
        return to;
    }

    @Override
    public WeatherDto convertFrom(Weather weather) {
        WeatherDto from = new WeatherDto();
        from.setCity(weather.getCity());
        from.setAvgHumidity(weather.getAvgHumidity());
        from.setDate(weather.getDate());
        from.setEpoch(weather.getEpoch());
        from.setIconUrl(weather.getIconUrl());
        from.setMaxTemp(weather.getMaxTemp());
        from.setMaxWind(weather.getMaxWind());
        from.setMinTemp(weather.getMinTemp());
        from.setText(weather.getText());
        return from;
    }
}
