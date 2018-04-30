package com.danieh.data.repository.datasource;

import com.danieh.data.cache.RepositoryCache;
import com.danieh.data.entitiy.RepositoryEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link RepoDataStore} implementation based on file system data store.
 */
class DiskRepoDataStore implements RepoDataStore {

    private final RepositoryCache repoCache;

    DiskRepoDataStore(RepositoryCache repoCache) {
        this.repoCache = repoCache;
    }

    @Override
    public Observable<List<RepositoryEntity>> repoEntityList(Integer pageCount) {
        return this.repoCache.get();
    }
}
