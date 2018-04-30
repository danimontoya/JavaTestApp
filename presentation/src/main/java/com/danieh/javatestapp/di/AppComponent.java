package com.danieh.javatestapp.di;

import android.content.Context;

import com.danieh.domain.executor.PostExecutionThread;
import com.danieh.domain.executor.ThreadExecutor;
import com.danieh.domain.repository.RepoRepository;
import com.danieh.javatestapp.TestApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import io.realm.Realm;

/**
 * Created by danieh
 */

@Singleton
@Component(modules = {AppModule.class, AndroidInjectorModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(TestApplication application);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    RepoRepository repoRepository();

    Realm realm();
}
