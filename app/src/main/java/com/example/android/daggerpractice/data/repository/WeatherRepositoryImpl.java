package com.example.android.daggerpractice.data.repository;

import android.util.Log;

import com.example.android.daggerpractice.data.DomainWeatherConverter;
import com.example.android.daggerpractice.data.WeatherDto;
import com.example.android.daggerpractice.data.db.DbDtoConverter;
import com.example.android.daggerpractice.data.db.WeatherDatabase;
import com.example.android.daggerpractice.data.db.model.WeatherLocal;
import com.example.android.daggerpractice.data.network.DtoConverter;
import com.example.android.daggerpractice.data.network.WeatherService;
import com.example.android.daggerpractice.data.network.model.RemoteWeather;
import com.example.android.daggerpractice.data.network.model.WeatherResponse;
import com.example.android.daggerpractice.domain.model.DomainWeather;
import com.example.android.daggerpractice.domain.WeatherRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class WeatherRepositoryImpl implements WeatherRepository {

    public static final String API_KEY = "36fb2efac1e148468fd131639190202";

    private final WeatherDatabase db;
    private final WeatherService weatherService;

    @Inject
    public WeatherRepositoryImpl(WeatherDatabase database, WeatherService service) {
        db = database;
        weatherService = service;
    }

    @Override
    public List<DomainWeather> getRemoteWeatherList(String city, String days) {
        List<WeatherDto> weatherDtoList = null;
        try {
            Response<WeatherResponse> response = weatherService.getWeatherList(API_KEY, city, days, "ru").execute();
            if (response.isSuccessful() && response.body() != null) {
                List<RemoteWeather> remoteWeatherList = response.body().getWeatherList().getItems();
                weatherDtoList = new DtoConverter(city).convertToAll(remoteWeatherList);
            } else {
                Log.i(getClass().getSimpleName(), "not successful");
            }
        } catch (Exception ex) {
            Log.e(getClass().getSimpleName(), "getWeatherListSync: ", ex);
        }
        List<WeatherLocal> weatherLocalList = new DbDtoConverter(city).convertFromAll(weatherDtoList);
        insertToDb(weatherLocalList);
        return new DomainWeatherConverter().convertToAll(weatherDtoList);
    }

    @Override
    public DomainWeather getWeatherLocal(String city, long epoch) {
        WeatherDto weatherDto = new DbDtoConverter(city).convertTo(db.getWeatherDao().getWeather(city, epoch));

        return new DomainWeatherConverter().convertTo(weatherDto);
    }

    public void insertToDb(List<WeatherLocal> weatherLocalList) {
        for (WeatherLocal weatherLocal : weatherLocalList) {
            db.getWeatherDao().insert(weatherLocal);
        }
    }
}
