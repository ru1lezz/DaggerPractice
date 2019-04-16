package com.example.android.daggerpractice.data;

import com.example.android.daggerpractice.domain.Converter;
import com.example.android.daggerpractice.domain.model.DomainWeather;

public class DomainWeatherConverter extends Converter<WeatherDto, DomainWeather> {
    @Override
    public DomainWeather convertTo(WeatherDto from) {
        DomainWeather to = new DomainWeather();
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
    public WeatherDto convertFrom(DomainWeather domainWeather) {
        WeatherDto from = new WeatherDto();
        from.setCity(domainWeather.getCity());
        from.setAvgHumidity(domainWeather.getAvgHumidity());
        from.setDate(domainWeather.getDate());
        from.setEpoch(domainWeather.getEpoch());
        from.setIconUrl(domainWeather.getIconUrl());
        from.setMaxTemp(domainWeather.getMaxTemp());
        from.setMaxWind(domainWeather.getMaxWind());
        from.setMinTemp(domainWeather.getMinTemp());
        from.setText(domainWeather.getText());
        return from;
    }
}
