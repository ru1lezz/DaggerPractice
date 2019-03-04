package com.example.android.daggerpractice.data.db;

import com.example.android.daggerpractice.data.WeatherDto;
import com.example.android.daggerpractice.data.db.model.WeatherLocal;
import com.example.android.daggerpractice.domain.Converter;

public class DbDtoConverter extends Converter<WeatherLocal, WeatherDto> {
    private final String city;

    public DbDtoConverter(String city) {
        this.city = city;
    }


    @Override
    public WeatherDto convertTo(WeatherLocal weatherLocal) {
        WeatherDto to = new WeatherDto();
        to.setText(weatherLocal.getText());
        to.setMinTemp(weatherLocal.getMinTemp());
        to.setMaxWind(weatherLocal.getMaxWind());
        to.setMaxTemp(weatherLocal.getMaxTemp());
        to.setIconUrl(weatherLocal.getIconUrl());
        to.setEpoch(weatherLocal.getEpoch());
        to.setDate(weatherLocal.getDate());
        to.setAvgHumidity(weatherLocal.getAvgHumidity());
        to.setCity(city);
        return to;
    }

    @Override
    public WeatherLocal convertFrom(WeatherDto weatherDto) {
        WeatherLocal from = new WeatherLocal();
        from.setAvgHumidity(weatherDto.getAvgHumidity());
        from.setCity(city);
        from.setDate(weatherDto.getDate());
        from.setEpoch(weatherDto.getEpoch());
        from.setIconUrl(weatherDto.getIconUrl());
        from.setMaxTemp(weatherDto.getMaxTemp());
        from.setMaxWind(weatherDto.getMaxWind());
        from.setMinTemp(weatherDto.getMinTemp());
        from.setText(weatherDto.getText());
        return from;
    }
}
