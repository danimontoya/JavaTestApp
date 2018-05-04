package com.danieh.data.repository;

import com.danieh.data.entitiy.mapper.RepositoryEntityDataMapper;
import com.danieh.data.repository.datasource.RepoDataStoreFactory;
import com.danieh.domain.model.Repository;
import com.danieh.domain.repository.RepoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by danieh
 */
public class RepoDataRepository implements RepoRepository {

    private final RepoDataStoreFactory repoDataStoreFactory;
    private final RepositoryEntityDataMapper repoEntityDataMapper;

    @Inject
    RepoDataRepository(RepoDataStoreFactory dataStoreFactory,
                       RepositoryEntityDataMapper entityDataMapper) {
        this.repoDataStoreFactory = dataStoreFactory;
        this.repoEntityDataMapper = entityDataMapper;
    }

    @Override
    public Observable<List<Repository>> repositories(Integer pageCount) {
        return repoDataStoreFactory.create().repoEntityList(pageCount).map(repoEntityDataMapper::transform);
    }
}
