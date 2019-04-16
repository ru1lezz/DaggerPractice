package com.example.android.daggerpractice.data;

import com.example.android.daggerpractice.domain.Converter;
import com.example.android.daggerpractice.domain.model.DomainSharedPref;

public class DomainSharedPrefConverter extends Converter<SharedPrefDto, DomainSharedPref> {
    @Override
    public DomainSharedPref convertTo(SharedPrefDto sharedPrefDto) {
        DomainSharedPref to = new DomainSharedPref();
        to.setCity(sharedPrefDto.getCity());
        to.setDays(sharedPrefDto.getDays());
        return to;
    }

    @Override
    public SharedPrefDto convertFrom(DomainSharedPref domainSharedPref) {
        SharedPrefDto from = new SharedPrefDto();
        from.setCity(domainSharedPref.getCity());
        from.setDays(domainSharedPref.getDays());
        return from;
    }
}
