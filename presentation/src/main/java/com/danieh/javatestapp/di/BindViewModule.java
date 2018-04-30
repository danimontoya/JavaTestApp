package com.danieh.javatestapp.di;

import com.danieh.javatestapp.ui.main.MainActivity;
import com.danieh.javatestapp.ui.main.MainView;

import dagger.Binds;
import dagger.Module;

/**
 * Created by danieh
 */

@Module
public abstract class BindViewModule {

    @Binds
    public abstract MainView bindMainView(MainActivity mainActivity);

}
