package com.example.android.daggerpractice.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Converter<From, To> {

    public abstract To convertTo(From from);

    public abstract From convertFrom(To to);

    public final List<To> convertToAll(List<From> from) {
        List<To> to = new ArrayList<>();
        for(From item : from) {
            to.add(convertTo(item));
        }
        return to;
    }

    public final List<From> convertFromAll(List<To> to) {
        List<From> from = new ArrayList<>();
        for(To item : to) {
            from.add(convertFrom(item));
        }
        return from;
    }
}
