package com.example.android.daggerpractice.presentation.presenter.weather;

import android.os.Handler;
import android.os.Looper;

import com.example.android.daggerpractice.domain.WeatherRepository;
import com.example.android.daggerpractice.domain.interactor.GetWeatherInteractor;
import com.example.android.daggerpractice.domain.model.Weather;
import com.example.android.daggerpractice.presentation.view.model.converter.DetailedWeatherConverter;
import com.example.android.daggerpractice.presentation.presenter.BasePresenter;
import com.example.android.daggerpractice.presentation.view.detailedweather.DetailedWeatherView;
import com.example.android.daggerpractice.presentation.view.model.DetailedWeatherUIModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherPresenter implements BasePresenter {
    private DetailedWeatherView mView;
    private WeatherRepository repository;

    private ExecutorService mExecutorService;
    private Handler mHandler;
    private String city;
    private long epoch;

    public WeatherPresenter(ExecutorService executorService, Handler handler, DetailedWeatherView mView, WeatherRepository repository, String city, long epoch) {
        this.mView = mView;
        this.repository = repository;
        this.city = city;
        this.epoch = epoch;
        mExecutorService = Executors.newSingleThreadExecutor();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {
        mExecutorService.execute(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");
            Date date = new Date(epoch * 1000);
            GetWeatherInteractor interactor = new GetWeatherInteractor(city, epoch, repository);
            Weather weather = interactor.execute();
            DetailedWeatherUIModel weatherUIModel = new DetailedWeatherConverter().convertTo(weather);
            mHandler.post(() -> {
                mView.setCity(city);
                mView.setDate(sdf.format(date));
                mView.setHighTemperature(String.valueOf(weatherUIModel.getMaxTemp()));
                mView.setLowTemperature(String.valueOf(weatherUIModel.getMinTemp()));
                mView.setHumidity(String.valueOf(weatherUIModel.getAvgHumidity()));
                mView.setWeatherDescription(weatherUIModel.getText());
                mView.setWind(String.valueOf(weatherUIModel.getMaxWind()));
                mView.setIconUrl("http:" + weatherUIModel.getUrlIcon());
            });
        });
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
