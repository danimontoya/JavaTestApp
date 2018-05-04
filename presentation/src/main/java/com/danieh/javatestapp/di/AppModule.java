package com.danieh.javatestapp.di;

import android.app.Application;
import android.content.Context;

import com.danieh.data.cache.RepositoryCache;
import com.danieh.data.cache.RepositoryCacheImpl;
import com.danieh.data.executor.JobExecutor;
import com.danieh.data.net.RestApi;
import com.danieh.data.net.RestApiImpl;
import com.danieh.data.repository.RepoDataRepository;
import com.danieh.domain.executor.PostExecutionThread;
import com.danieh.domain.executor.ThreadExecutor;
import com.danieh.domain.repository.RepoRepository;
import com.danieh.javatestapp.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by danieh on 4/28/18.
 */

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    RepositoryCache provideRepositoryCache(RepositoryCacheImpl repositoryCache) {
        return repositoryCache;
    }

    @Provides
    @Singleton
    RestApi provideRestApi(RestApiImpl restApi) {
        return restApi;
    }

    @Provides
    @Singleton
    RepoRepository provideRepoRepository(RepoDataRepository repoDataRepository) {
        return repoDataRepository;
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        Realm.init(application.getApplicationContext());
        return Realm.getDefaultInstance();
    }
}
