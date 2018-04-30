package com.danieh.javatestapp.di;

import com.danieh.javatestapp.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by danieh
 */

@Module
public abstract class AndroidInjectorModule {

    @ContributesAndroidInjector(modules = BindViewModule.class)
    public abstract MainActivity injectMainActivity();
}
