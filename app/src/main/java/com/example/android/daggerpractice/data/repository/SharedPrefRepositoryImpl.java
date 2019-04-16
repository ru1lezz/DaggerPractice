package com.example.android.daggerpractice.data.repository;

import android.content.Context;

import com.example.android.daggerpractice.data.DomainSharedPrefConverter;
import com.example.android.daggerpractice.domain.model.DomainSharedPref;
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
    public DomainSharedPref getSharedPref() {
        return new DomainSharedPrefConverter().convertTo(sharedPrefDataStorage.getPreferences());
    }

    @Override
    public void setSharedPref(DomainSharedPref domainSharedPref) {
        sharedPrefDataStorage.setPreferences(new DomainSharedPrefConverter().convertFrom(domainSharedPref));
    }
}
