package com.example.android.daggerpractice.domain.interactor;

import com.example.android.daggerpractice.domain.SharedPrefRepository;
import com.example.android.daggerpractice.domain.model.SharedPref;

public class GetSharedPrefInteractor {
    private final SharedPrefRepository repository;

    public GetSharedPrefInteractor(SharedPrefRepository repository) {
        this.repository = repository;
    }

    public SharedPref execute() { return repository.getSharedPref();}
}
