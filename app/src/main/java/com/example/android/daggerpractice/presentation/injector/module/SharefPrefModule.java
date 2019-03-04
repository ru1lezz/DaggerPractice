package com.example.android.daggerpractice.presentation.injector.module;

import com.example.android.daggerpractice.domain.interactor.GetSharedPrefInteractor;
import com.example.android.daggerpractice.domain.interactor.SetSharedPrefInteractor;
import com.example.android.daggerpractice.presentation.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class SharefPrefModule {
    @Provides
    @PerActivity
    GetSharedPrefInteractor provideGetSharedPrefInteractor(GetSharedPrefInteractor getSharedPrefInteractor) {
        return getSharedPrefInteractor;
    }

    @Provides
    @PerActivity
    SetSharedPrefInteractor provideSetSharedPrefInteractor(SetSharedPrefInteractor setSharedPrefInteractor) {
        return setSharedPrefInteractor;
    }
}
