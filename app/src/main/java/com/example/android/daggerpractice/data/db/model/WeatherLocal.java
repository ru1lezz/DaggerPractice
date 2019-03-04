package com.example.android.daggerpractice.data.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

@Entity
public class WeatherLocal {
    @PrimaryKey
    private long epoch;
    private String date;
    private String city;
    private double maxTemp;
    private double minTemp;
    private double maxWind;
    private double avgHumidity;
    private String text;
    private String iconUrl;

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxWind() {
        return maxWind;
    }

    public void setMaxWind(double maxWind) {
        this.maxWind = maxWind;
    }

    public double getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(double avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherLocal weatherLocal = (WeatherLocal) o;
        return epoch == weatherLocal.epoch &&
                Double.compare(weatherLocal.maxTemp, maxTemp) == 0 &&
                Double.compare(weatherLocal.minTemp, minTemp) == 0 &&
                Double.compare(weatherLocal.maxWind, maxWind) == 0 &&
                Double.compare(weatherLocal.avgHumidity, avgHumidity) == 0 &&
                Objects.equals(date, weatherLocal.date) &&
                Objects.equals(city, weatherLocal.city) &&
                Objects.equals(text, weatherLocal.text) &&
                Objects.equals(iconUrl, weatherLocal.iconUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(epoch, date, city, maxTemp, minTemp, maxWind, avgHumidity, text, iconUrl);
    }
}
