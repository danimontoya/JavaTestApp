package com.danieh.data.repository.datasource;

import com.danieh.data.cache.RepositoryCache;
import com.danieh.data.entitiy.RepositoryEntity;
import com.danieh.data.net.RestApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link RepoDataStore} implementation based on connections to the api (Cloud).
 */
class CloudRepoDataStore implements RepoDataStore {

    private final RestApi restApi;
    private final RepositoryCache repositoryCache;

    /**
     * Construct a {@link RepoDataStore} based on connections to the api (Cloud).
     *
     * @param restApi   The {@link RestApi} implementation to use.
     * @param repoCache A {@link RepositoryCache} to cache data retrieved from the api.
     */
    CloudRepoDataStore(RestApi restApi, RepositoryCache repoCache) {
        this.restApi = restApi;
        this.repositoryCache = repoCache;
    }

    @Override
    public Observable<List<RepositoryEntity>> repoEntityList(Integer pageCount) {
        return restApi.repoEntityList(pageCount).doOnNext(repositoryCache::put);
    }
}
