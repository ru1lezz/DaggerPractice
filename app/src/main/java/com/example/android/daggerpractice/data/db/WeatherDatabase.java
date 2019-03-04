package com.example.android.daggerpractice.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.android.daggerpractice.data.db.model.WeatherLocal;

@Database(entities = WeatherLocal.class, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDao getWeatherDao();
}
