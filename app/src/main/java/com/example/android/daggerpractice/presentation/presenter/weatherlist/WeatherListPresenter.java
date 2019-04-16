package com.example.android.daggerpractice.presentation.presenter.weatherlist;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import com.example.android.daggerpractice.R;
import com.example.android.daggerpractice.domain.SharedPrefRepository;
import com.example.android.daggerpractice.domain.interactor.GetSharedPrefInteractor;
import com.example.android.daggerpractice.domain.interactor.GetWeatherListInteractor;
import com.example.android.daggerpractice.domain.interactor.SetSharedPrefInteractor;
import com.example.android.daggerpractice.domain.model.DomainWeather;
import com.example.android.daggerpractice.injector.PerActivity;
import com.example.android.daggerpractice.presentation.view.model.converter.DomainSharedPrefConverter;
import com.example.android.daggerpractice.presentation.view.model.converter.WeatherUiModelConverter;
import com.example.android.daggerpractice.presentation.presenter.BasePresenter;
import com.example.android.daggerpractice.presentation.view.model.SharedPref;
import com.example.android.daggerpractice.presentation.view.model.Weather;
import com.example.android.daggerpractice.presentation.view.weatherlist.MainView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

@PerActivity
public class WeatherListPresenter implements BasePresenter {

    private Resources mResources;
    private MainView mView;
    private GetWeatherListInteractor mGetWeatherListInteractor;
    private SharedPrefRepository mSharedPrefRepository;
    private SharedPref mSharedPref;
    private ExecutorService mExecutorService;
    private Handler mHandler;

    @Inject
    public WeatherListPresenter(GetWeatherListInteractor getWeatherListInteractor, SharedPrefRepository sharedPrefRepository, Resources resources) {
        mGetWeatherListInteractor = getWeatherListInteractor;
        mSharedPrefRepository = sharedPrefRepository;
        mResources = resources;
    }

    public void setView(MainView view) {
        mView = view;
        mSharedPref = new DomainSharedPrefConverter().convertFrom(new GetSharedPrefInteractor(mSharedPrefRepository).execute());
        mView.setCity(mSharedPref.getCity());
        mView.setDays(mSharedPref.getDays()-1);
    }

    @Override
    public void onCreate() {
        mExecutorService = Executors.newSingleThreadExecutor();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @SuppressLint("CheckResult")
    @Override
    public void onResume() {
        mGetWeatherListInteractor.setCity(mSharedPref.getCity());
        mGetWeatherListInteractor.setDays(String.valueOf(mSharedPref.getDays()));
        mExecutorService.execute(() -> {
            List<DomainWeather> domainWeatherList = mGetWeatherListInteractor.execute();
            List<Weather> newList = new WeatherUiModelConverter().convertToAll(domainWeatherList);
            mHandler.post(() -> {
                mView.displayWeatherList(newList);
                mView.setCity(mSharedPref.getCity());
                setSpinnerPosition();
            });
        });
//        GetWeatherListReactiveInteractor interactor = new GetWeatherListReactiveInteractor(mSharedPref.getCity(), String.valueOf(mSharedPref.getDays()), mWeatherRepository);
//        interactor.execute()
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(weathers -> new WeatherUiModelConverter().convertToAll(weathers))
//                .subscribe(weatherUIModels -> mView.displayWeatherList(weatherUIModels));
//        setSpinnerPosition();
    }

    private void setSpinnerPosition() {
        int[] days = mResources.getIntArray(R.array.days_array_values);
        for (int i = 0; i < days.length; i++) {
            if(days[i] == mSharedPref.getDays()) {
                mView.setDays(i);
                return;
            }
        }
    }

    @Override
    public void onLoad() {
        mExecutorService.execute(() -> {
            List<DomainWeather> domainWeatherList = mGetWeatherListInteractor.execute();
            List<Weather> newList = new WeatherUiModelConverter().convertToAll(domainWeatherList);
            mHandler.post(() -> mView.displayWeatherList(newList));
        });
        new SetSharedPrefInteractor(mSharedPrefRepository, new DomainSharedPrefConverter().convertTo(mSharedPref)).execute();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    public void onSpinnerItemSelected(int days) {
        mSharedPref.setDays(days);
    }

    public void onCityEditTextChanged(String city) {
        mSharedPref.setCity(city);
    }

}