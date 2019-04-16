package com.example.android.daggerpractice.presentation.view.detailedweather;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.daggerpractice.R;
import com.example.android.daggerpractice.injector.component.ApplicationComponent;
import com.example.android.daggerpractice.injector.component.DaggerWeatherComponent;
import com.example.android.daggerpractice.injector.module.ActivityModule;
import com.example.android.daggerpractice.injector.module.SharefPrefModule;
import com.example.android.daggerpractice.injector.module.WeatherModule;
import com.example.android.daggerpractice.presentation.WeatherApplication;
import com.example.android.daggerpractice.presentation.presenter.weather.WeatherPresenter;

import javax.inject.Inject;

public class DetailedWeatherActivity extends AppCompatActivity implements DetailedWeatherView{

    private static final String WEATHER_CITY = "weather_city";
    private static final String WEATHER_EPOCH = "weather_epoch";

    @Inject
    WeatherPresenter mPresenter;

    private TextView mHighTemperatureTextView;
    private TextView mLowTemperatureTextView;
    private TextView mDateTextView;
    private TextView mWeatherDescription;
    private TextView mHumidity;
    private TextView mWind;
    private ImageView mIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);
        initViews();
        initInjector(getApplicationComponent());
        initPresenter();
    }

    private void initViews() {
        mHighTemperatureTextView = findViewById(R.id.high_temperature);
        mLowTemperatureTextView = findViewById(R.id.low_temperature);
        mDateTextView = findViewById(R.id.date);
        mWeatherDescription = findViewById(R.id.weather_description);
        mHumidity = findViewById(R.id.humidity);
        mWind = findViewById(R.id.wind_measurement);
        mIcon = findViewById(R.id.weather_icon);
    }

    private void initPresenter() {
        mPresenter.setView(DetailedWeatherActivity.this);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            if(bundle.containsKey(WEATHER_CITY) && bundle.containsKey(WEATHER_EPOCH)) {
                mPresenter.setCity(bundle.getString(WEATHER_CITY));
                mPresenter.setEpoch(bundle.getLong(WEATHER_EPOCH));
            }
        }
        mPresenter.onCreate();
    }

    private ApplicationComponent getApplicationComponent() {
        return ((WeatherApplication) getApplication()).getApplicationComponent();
    }

    private void initInjector(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
        DaggerWeatherComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this))
                .weatherModule(new WeatherModule())
                .sharefPrefModule(new SharefPrefModule())
                .build()
                .inject(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    public static Intent newIntent(Context context, String city, long epoch) {
        Intent intent = new Intent(context, DetailedWeatherActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(WEATHER_CITY, city);
        bundle.putLong(WEATHER_EPOCH, epoch);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void setCity(String city) {

    }

    @Override
    public void setDate(String date) {
        mDateTextView.setText(date);
    }

    @Override
    public void setWeatherDescription(String description) {
        mWeatherDescription.setText(description);
    }

    @Override
    public void setHumidity(String humidity) {
        mHumidity.setText(humidity);
    }

    @Override
    public void setHighTemperature(String highTemperature) {
        mHighTemperatureTextView.setText(highTemperature);
    }

    @Override
    public void setLowTemperature(String lowTemperature) {
        mLowTemperatureTextView.setText(lowTemperature);
    }

    @Override
    public void setWind(String wind) {
        mWind.setText(wind);
    }

    @Override
    public void setIconUrl(String url) {
        Glide.with(DetailedWeatherActivity.this)
                .load(url)
                .into(mIcon);
    }
}