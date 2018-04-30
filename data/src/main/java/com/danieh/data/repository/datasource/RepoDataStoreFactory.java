package com.danieh.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.danieh.data.cache.RepositoryCache;
import com.danieh.data.entitiy.mapper.RepositoryEntityJsonMapper;
import com.danieh.data.net.RestApi;
import com.danieh.data.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by danieh
 */
@Singleton
public class RepoDataStoreFactory {

    private final Context context;
    private final RepositoryCache repositoryCache;

    private final RepositoryEntityJsonMapper entityJsonMapper;
    private final RestApi restApi;

    @Inject
    RepoDataStoreFactory(@NonNull Context context, @NonNull RepositoryCache repositoryCache) {
        this.context = context.getApplicationContext();
        this.repositoryCache = repositoryCache;
        this.entityJsonMapper = new RepositoryEntityJsonMapper();
        this.restApi = new RestApiImpl(context, entityJsonMapper);
    }

    public RepoDataStore create() {
        if (!restApi.isNetworkAvailable() && !repositoryCache.isExpired() && repositoryCache.isCached()) {
            return new DiskRepoDataStore(repositoryCache);
        } else {
            return new CloudRepoDataStore(restApi, repositoryCache);
        }
    }
}
