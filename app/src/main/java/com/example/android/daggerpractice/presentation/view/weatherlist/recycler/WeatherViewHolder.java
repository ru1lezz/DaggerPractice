package com.example.android.daggerpractice.presentation.view.weatherlist.recycler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.daggerpractice.R;
import com.example.android.daggerpractice.presentation.view.detailedweather.DetailedWeatherActivity;
import com.example.android.daggerpractice.presentation.view.model.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ImageView imageView;
    private TextView mWeatherDate, mMaxTemperature, mLowTemperature, mWeatherDescription;
    private Weather mWeather;

    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
        itemView.setOnClickListener(this);
    }

    private void initViews(View itemView) {
        mWeatherDate = itemView.findViewById(R.id.weather_date_text_view);
        mMaxTemperature = itemView.findViewById(R.id.weather_max_temperature_text_view);
        mLowTemperature = itemView.findViewById(R.id.weather_low_temperature_text_view);
        mWeatherDescription = itemView.findViewById(R.id.weather_description_text_view);
        imageView = itemView.findViewById(R.id.weather_icon);
    }

    public void bind(Weather weather) {
        mWeather = weather;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");
        mMaxTemperature.setText(String.valueOf(mWeather.getMaxTemp()));
        mLowTemperature.setText(String.valueOf(mWeather.getMinTemp()));
        mWeatherDescription.setText(mWeather.getText());
        mWeatherDate.setText(sdf.format(new Date(mWeather.getEpoch() * 1000)));
        String url = "http:" + mWeather.getUrlIcon();
        Glide.with(itemView)
                .load(url)
                .into(imageView);

    }

    @Override
    public void onClick(View v) {
        Intent intent = DetailedWeatherActivity.newIntent(v.getContext(), mWeather.getCity(), mWeather.getEpoch());
        v.getContext().startActivity(intent);
    }
}