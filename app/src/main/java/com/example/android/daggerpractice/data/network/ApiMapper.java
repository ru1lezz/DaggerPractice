package com.example.android.daggerpractice.data.network;

import android.util.Log;

import com.example.android.daggerpractice.data.WeatherDto;
import com.example.android.daggerpractice.data.network.model.Weather;
import com.example.android.daggerpractice.data.network.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class ApiMapper {
    public static final String API_KEY = "36fb2efac1e148468fd131639190202";
    private RetrofitHelper retrofitHelper;

    public ApiMapper(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

//    public void getWeatherListAsync(Callback<WeatherResponse> callback) {
//        retrofitHelper.getService().getWeatherList(API_KEY, "moscow", "10", "ru").enqueue(callback);
//    }

    public List<WeatherDto> getWeatherListSync(String city, String days) {
        try {
            Response<WeatherResponse> response = retrofitHelper.getService().getWeatherList(API_KEY, city, days, "ru").execute();
            if (response.isSuccessful() && response.body() != null) {
                List<Weather> weatherList = response.body().getWeatherList().getItems();
                return new DtoConverter(city).convertToAll(weatherList);
            } else {
                Log.i(getClass().getSimpleName(), "not successful");
            }
        } catch (Exception ex) {
            Log.e(ApiMapper.class.getSimpleName(), "getWeatherListSync: ", ex);
        }
        return new ArrayList<>();
    }
}
