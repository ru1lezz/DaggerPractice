package com.example.android.daggerpractice.domain;

import com.example.android.daggerpractice.domain.model.DomainSharedPref;

public interface SharedPrefRepository {
    DomainSharedPref getSharedPref();

    void setSharedPref(DomainSharedPref domainSharedPref);
}
