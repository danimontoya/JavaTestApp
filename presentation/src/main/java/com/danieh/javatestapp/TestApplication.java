package com.danieh.javatestapp;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

import com.danieh.javatestapp.di.AppModule;
import com.danieh.javatestapp.di.DaggerAppComponent;
import com.danieh.javatestapp.utils.ScreenUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;

/**
 * Created by danieh
 */

public class TestApplication extends Application implements HasActivityInjector {

//    private AppComponent appComponent;

    @NonNull
    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
        ScreenUtils.init(this);
        Realm.init(this);
    }

    private void initDagger() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
