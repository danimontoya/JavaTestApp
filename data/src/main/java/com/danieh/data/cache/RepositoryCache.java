package com.danieh.data.cache;

import com.danieh.data.entitiy.RepositoryEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * An interface representing a repository Cache.
 */
public interface RepositoryCache {

    boolean isCached();

    boolean isExpired();

    Observable<List<RepositoryEntity>> get();

    void put(List<RepositoryEntity> repositoryEntities);

}
