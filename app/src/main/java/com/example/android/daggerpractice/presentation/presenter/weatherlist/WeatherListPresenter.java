package com.example.android.daggerpractice.presentation.presenter.weatherlist;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import com.example.android.daggerpractice.R;
import com.example.android.daggerpractice.domain.SharedPrefRepository;
import com.example.android.daggerpractice.domain.WeatherRepository;
import com.example.android.daggerpractice.domain.interactor.GetSharedPrefInteractor;
import com.example.android.daggerpractice.domain.interactor.GetWeatherListInteractor;
import com.example.android.daggerpractice.domain.interactor.SetSharedPrefInteractor;
import com.example.android.daggerpractice.domain.model.Weather;
import com.example.android.daggerpractice.presentation.injector.PerActivity;
import com.example.android.daggerpractice.presentation.view.model.converter.DomainSharedPrefConverter;
import com.example.android.daggerpractice.presentation.view.model.converter.WeatherUIModelConverter;
import com.example.android.daggerpractice.presentation.presenter.BasePresenter;
import com.example.android.daggerpractice.presentation.view.model.SharedPref;
import com.example.android.daggerpractice.presentation.view.model.WeatherUIModel;
import com.example.android.daggerpractice.presentation.view.weatherlist.MainActivity;
import com.example.android.daggerpractice.presentation.view.weatherlist.MainView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

@PerActivity
public class WeatherListPresenter implements BasePresenter {

    private Resources resources;
    private MainView mView;
    private WeatherRepository mWeatherRepository;
    private SharedPrefRepository sharedPrefRepository;
    private SharedPref sharedPref;
    private ExecutorService mExecutorService;
    private Handler mHandler;

    @Inject
    public WeatherListPresenter(WeatherRepository weatherRepository, SharedPrefRepository sharedPrefRepository, Resources resources) {
        this.mWeatherRepository = weatherRepository;
        this.sharedPrefRepository = sharedPrefRepository;
        this.resources = resources;
        mExecutorService = Executors.newSingleThreadExecutor();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void setView(MainView view) {
        mView = view;
        mExecutorService.execute(() -> {
            sharedPref = new DomainSharedPrefConverter().convertFrom(new GetSharedPrefInteractor(sharedPrefRepository).execute());
            mHandler.post(() -> {
                mView.setCity(sharedPref.getCity());
                mView.setDays(sharedPref.getDays()-1);
            });
        });
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {
        mExecutorService.execute(() -> {
            GetWeatherListInteractor getWeatherListInteractor = new GetWeatherListInteractor(sharedPref.getCity(), String.valueOf(sharedPref.getDays()-1), mWeatherRepository);
            List<Weather> weatherList = getWeatherListInteractor.execute();
            List<WeatherUIModel> newList = new WeatherUIModelConverter().convertToAll(weatherList);
            mHandler.post(() -> {
                mView.displayWeatherList(newList);
                mView.setCity(sharedPref.getCity());
                setSpinnerPosition();
            });
        });
    }

    private void setSpinnerPosition() {
        int[] days = resources.getIntArray(R.array.days_array_values);
        for (int i = 0; i < days.length; i++) {
            if(days[i] == sharedPref.getDays()) {
                mView.setDays(i);
                return;
            }
        }
    }

    @Override
    public void onLoad() {
        mExecutorService.execute(() -> {
            GetWeatherListInteractor getWeatherListInteractor = new GetWeatherListInteractor(sharedPref.getCity(), String.valueOf(sharedPref.getDays()-1), mWeatherRepository);
            List<Weather> weatherList = getWeatherListInteractor.execute();
            List<WeatherUIModel> newList = new WeatherUIModelConverter().convertToAll(weatherList);
            mHandler.post(() -> mView.displayWeatherList(newList));
        });
        new SetSharedPrefInteractor(sharedPrefRepository, new DomainSharedPrefConverter().convertTo(sharedPref)).execute();
    }

    @Override
    public void onDestroy() {

    }

    public void onSpinnerItemSelected(int days) {
        sharedPref.setDays(days);
    }

    public void onCityEditTextChanged(String city) {
        sharedPref.setCity(city);
    }

}