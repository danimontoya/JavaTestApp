package com.danieh.data.entitiy.mapper;

import android.support.annotation.NonNull;

import com.danieh.data.entitiy.RepositoryEntity;
import com.danieh.domain.mapper.DataMapper;
import com.danieh.domain.model.Repository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link RepositoryEntity} (in the data layer) to {@link Repository} in the
 * domain layer.
 */
@Singleton
public class RepositoryEntityDataMapper extends DataMapper<RepositoryEntity, Repository> {

    @Inject
    RepositoryEntityDataMapper() {
    }

    @NonNull
    @Override
    public Repository transform(@NonNull RepositoryEntity repoEntity) {
        return new Repository(repoEntity.getId(),
                repoEntity.getName(),
                repoEntity.getDescription(),
                repoEntity.getLanguage(),
                repoEntity.getStargazersCount(),
                repoEntity.getForks(),
                repoEntity.getUpdatedAt());
    }
}
