package com.example.android.daggerpractice.domain.model;

import java.util.Objects;

public class DomainWeather {
    private String city;
    private long epoch;
    private String date;
    private double maxTemp;
    private double minTemp;
    private double maxWind;
    private double avgHumidity;
    private String text;
    private String iconUrl;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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
        DomainWeather domainWeather = (DomainWeather) o;
        return epoch == domainWeather.epoch &&
                Double.compare(domainWeather.maxTemp, maxTemp) == 0 &&
                Double.compare(domainWeather.minTemp, minTemp) == 0 &&
                Double.compare(domainWeather.maxWind, maxWind) == 0 &&
                Double.compare(domainWeather.avgHumidity, avgHumidity) == 0 &&
                Objects.equals(city, domainWeather.city) &&
                Objects.equals(date, domainWeather.date) &&
                Objects.equals(text, domainWeather.text) &&
                Objects.equals(iconUrl, domainWeather.iconUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, epoch, date, maxTemp, minTemp, maxWind, avgHumidity, text, iconUrl);
    }
}
