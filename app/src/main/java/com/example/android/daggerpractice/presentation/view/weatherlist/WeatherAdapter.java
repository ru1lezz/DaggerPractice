package com.example.android.daggerpractice.presentation.view.weatherlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.daggerpractice.R;
import com.example.android.daggerpractice.presentation.view.model.WeatherUIModel;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private final Context mContext;
    private List<WeatherUIModel> mWeatherList;

    public WeatherAdapter(Context context) {
        mContext = context;
        mWeatherList = new ArrayList<>();
    }

    public void setmWeatherList(List<WeatherUIModel> weatherList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(mWeatherList, weatherList));
        diffResult.dispatchUpdatesTo(this);
        mWeatherList.clear();
        mWeatherList.addAll(weatherList);
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_list_item, viewGroup, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
        weatherViewHolder.bind(mWeatherList.get(i));
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }
}