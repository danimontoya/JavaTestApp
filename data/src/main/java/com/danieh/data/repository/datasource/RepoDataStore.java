package com.danieh.data.repository.datasource;

import com.danieh.data.entitiy.RepositoryEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface RepoDataStore {

    Observable<List<RepositoryEntity>> repoEntityList(Integer pageCount);
}
