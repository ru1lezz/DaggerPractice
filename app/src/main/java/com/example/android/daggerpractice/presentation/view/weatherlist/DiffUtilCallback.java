package com.example.android.daggerpractice.presentation.view.weatherlist;

import android.support.v7.util.DiffUtil;

import com.example.android.daggerpractice.presentation.view.model.WeatherUIModel;

import java.util.List;

public class DiffUtilCallback extends DiffUtil.Callback {
    private final List<WeatherUIModel> oldList;
    private final List<WeatherUIModel> newList;

    public DiffUtilCallback(List<WeatherUIModel> oldList, List<WeatherUIModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return oldList.get(i).getEpoch() == newList.get(i1).getEpoch();
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return oldList.get(i).equals(newList.get(i1));
    }
}
