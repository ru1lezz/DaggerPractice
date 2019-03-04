package com.example.android.daggerpractice.domain;

import com.example.android.daggerpractice.domain.model.SharedPref;

public interface SharedPrefRepository {
    SharedPref getSharedPref();

    void setSharedPref(SharedPref sharedPref);
}
