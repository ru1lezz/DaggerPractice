package com.example.android.daggerpractice.presentation.injector.module;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.android.daggerpractice.presentation.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @PerActivity
    AppCompatActivity provideActivity() { return appCompatActivity; }

    @Provides
    @PerActivity
    Resources getResources() { return appCompatActivity.getResources(); }

    @Provides
    @PerActivity
    RequestManager provideGlide() { return Glide.with(appCompatActivity); }
}
