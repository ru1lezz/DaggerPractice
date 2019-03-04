package com.example.android.daggerpractice.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.android.daggerpractice.data.db.model.WeatherLocal;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class WeatherDao {
    @Query("SELECT * FROM WeatherLocal")
    public abstract List<WeatherLocal> getAll();

    @Query("SELECT * FROM WeatherLocal WHERE city = :city and epoch = :epoch")
    public abstract WeatherLocal getWeather(String city, long epoch);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(WeatherLocal weatherLocal);

    @Query("DELETE FROM WeatherLocal")
    public abstract void delete();

    @Transaction
    public List<WeatherLocal> getWeatherListRange(String city, long[] epoch) {
        List<WeatherLocal> weatherLocalList = new ArrayList<>();
        for (long date : epoch) {
            weatherLocalList.add(getWeather(city, date));
        }
        return weatherLocalList;
    }
}
