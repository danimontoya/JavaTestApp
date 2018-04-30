package com.danieh.data.repository;

import com.danieh.data.entitiy.RepositoryEntity;
import com.danieh.data.entitiy.mapper.RepositoryEntityDataMapper;
import com.danieh.data.repository.datasource.RepoDataStoreFactory;
import com.danieh.domain.model.Repository;
import com.danieh.domain.repository.RepoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

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
        return repoDataStoreFactory.create().repoEntityList(pageCount).map(new Function<List<RepositoryEntity>, List<Repository>>() {
            @Override
            public List<Repository> apply(List<RepositoryEntity> dataEntities) throws Exception {
                return repoEntityDataMapper.transform(dataEntities);
            }
        });
    }
}
