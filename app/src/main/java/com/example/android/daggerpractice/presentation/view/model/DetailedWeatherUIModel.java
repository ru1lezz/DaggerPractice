package com.example.android.daggerpractice.presentation.view.model;

import java.util.Objects;

public class DetailedWeatherUIModel {
    private long epoch;
    private String date;
    private double minTemp;
    private double maxTemp;
    private String urlIcon;
    private String text;
    private double maxWind;
    private double avgHumidity;

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

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public void setUrlIcon(String urlIcon) {
        this.urlIcon = urlIcon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailedWeatherUIModel that = (DetailedWeatherUIModel) o;
        return epoch == that.epoch &&
                Double.compare(that.minTemp, minTemp) == 0 &&
                Double.compare(that.maxTemp, maxTemp) == 0 &&
                Double.compare(that.maxWind, maxWind) == 0 &&
                Double.compare(that.avgHumidity, avgHumidity) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(urlIcon, that.urlIcon) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(epoch, date, minTemp, maxTemp, urlIcon, text, maxWind, avgHumidity);
    }
}
