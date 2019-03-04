package com.example.android.daggerpractice.data.repository;

import android.content.Context;

import com.example.android.daggerpractice.data.DomainSharedPrefConverter;
import com.example.android.daggerpractice.domain.model.SharedPref;
import com.example.android.daggerpractice.data.sharedpref.SharedPrefDataStorage;
import com.example.android.daggerpractice.domain.SharedPrefRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharedPrefRepositoryImpl implements SharedPrefRepository {
    private SharedPrefDataStorage sharedPrefDataStorage;

    @Inject
    public SharedPrefRepositoryImpl(Context context) {
        sharedPrefDataStorage = new SharedPrefDataStorage(context);
    }

    @Override
    public SharedPref getSharedPref() {
        return new DomainSharedPrefConverter().convertTo(sharedPrefDataStorage.getPreferences());
    }

    @Override
    public void setSharedPref(SharedPref sharedPref) {
        sharedPrefDataStorage.setPreferences(new DomainSharedPrefConverter().convertFrom(sharedPref));
    }
}
