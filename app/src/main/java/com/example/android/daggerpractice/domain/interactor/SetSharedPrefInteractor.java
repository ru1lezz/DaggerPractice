package com.example.android.daggerpractice.domain.interactor;

import com.example.android.daggerpractice.domain.SharedPrefRepository;
import com.example.android.daggerpractice.domain.model.DomainSharedPref;

public class SetSharedPrefInteractor {
    private final SharedPrefRepository repository;
    private final DomainSharedPref domainSharedPref;

    public SetSharedPrefInteractor(SharedPrefRepository repository, DomainSharedPref domainSharedPref) {
        this.repository = repository;
        this.domainSharedPref = domainSharedPref;
    }

    public void execute() { repository.setSharedPref(domainSharedPref); }
}
