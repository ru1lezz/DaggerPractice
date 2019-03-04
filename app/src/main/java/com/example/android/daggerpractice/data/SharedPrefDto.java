package com.example.android.daggerpractice.data;

import java.util.Objects;

public class SharedPrefDto {
    private String city;
    private int days;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedPrefDto that = (SharedPrefDto) o;
        return days == that.days &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, days);
    }
}
