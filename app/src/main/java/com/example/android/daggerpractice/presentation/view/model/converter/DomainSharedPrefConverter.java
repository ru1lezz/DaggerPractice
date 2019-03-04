package com.example.android.daggerpractice.presentation.view.model.converter;

import com.example.android.daggerpractice.domain.Converter;
import com.example.android.daggerpractice.presentation.view.model.SharedPref;

public class DomainSharedPrefConverter extends Converter<SharedPref, com.example.android.daggerpractice.domain.model.SharedPref> {

    @Override
    public com.example.android.daggerpractice.domain.model.SharedPref convertTo(SharedPref sharedPref) {
        com.example.android.daggerpractice.domain.model.SharedPref to = new com.example.android.daggerpractice.domain.model.SharedPref();
        to.setDays(sharedPref.getDays());
        to.setCity(sharedPref.getCity());
        return to;
    }

    @Override
    public SharedPref convertFrom(com.example.android.daggerpractice.domain.model.SharedPref sharedPref) {
        SharedPref from = new SharedPref();
        from.setCity(sharedPref.getCity());
        from.setDays(sharedPref.getDays());
        return from;
    }
}