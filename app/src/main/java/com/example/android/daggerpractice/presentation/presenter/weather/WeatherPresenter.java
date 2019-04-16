package com.example.android.daggerpractice.presentation.presenter.weather;

import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.RequestManager;
import com.example.android.daggerpractice.domain.interactor.GetWeatherInteractor;
import com.example.android.daggerpractice.domain.model.DomainWeather;
import com.example.android.daggerpractice.presentation.view.model.converter.DetailedWeatherConverter;
import com.example.android.daggerpractice.presentation.presenter.BasePresenter;
import com.example.android.daggerpractice.presentation.view.detailedweather.DetailedWeatherView;
import com.example.android.daggerpractice.presentation.view.model.DetailedWeather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class WeatherPresenter implements BasePresenter {

    private DetailedWeatherView mView;
    private ExecutorService mExecutorService;
    private Handler mHandler;
    private RequestManager mGlide;
    @Inject
    GetWeatherInteractor mInteractor;

    private String mCity;
    private long mEpoch;

    @Inject
    public WeatherPresenter(GetWeatherInteractor interactor, RequestManager glide) {
        mInteractor = interactor;
        mGlide = glide;
    }

    public void setView(DetailedWeatherView view) {
        mView = view;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public void setEpoch(long epoch) {
        mEpoch = epoch;
    }

    @Override
    public void onCreate() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onResume() {
        mInteractor.setCity(mCity);
        mInteractor.setEpoch(mEpoch);
        mExecutorService.execute(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");
            Date date = new Date(mEpoch * 1000);
            DomainWeather domainWeather = mInteractor.execute();
            DetailedWeather weatherUIModel = new DetailedWeatherConverter().convertTo(domainWeather);
            mHandler.post(() -> {
                mView.setCity(mCity);
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
