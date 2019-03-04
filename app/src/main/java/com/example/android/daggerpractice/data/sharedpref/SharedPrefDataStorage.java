package com.example.android.daggerpractice.data.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android.daggerpractice.data.SharedPrefDto;

public class SharedPrefDataStorage {
    private static final String PREF_NAME = "preferences";
    private static final String CITY = "city";
    private static final String DAYS = "days";
    private static final String DEFAULT_CITY = "Moscow";
    private static final int DEFAULT_DAYS = 7;

    private final SharedPreferences mSharedPreferences;

    public SharedPrefDataStorage(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public SharedPrefDto getPreferences() {
        SharedPref preferencesDto = new SharedPref();
        preferencesDto.setCity(mSharedPreferences.getString(CITY, DEFAULT_CITY));
        preferencesDto.setDays(mSharedPreferences.getInt(DAYS, DEFAULT_DAYS));
        return new DtoConverter().convertTo(preferencesDto);
    }

    public void setPreferences(SharedPrefDto preferences) {
        SharedPref sharedPref = new DtoConverter().convertFrom(preferences);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(CITY, sharedPref.getCity());
        editor.putInt(DAYS, sharedPref.getDays());
        editor.apply();
    }
}
