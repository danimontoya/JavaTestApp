package com.danieh.javatestapp.mapper;

import android.support.annotation.NonNull;

import com.danieh.domain.mapper.DataMapper;
import com.danieh.domain.model.Repository;
import com.danieh.javatestapp.model.RepositoryModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by danieh
 */
@Singleton
public class RepositoryModelDataMapper extends DataMapper<Repository, RepositoryModel> {

    @Inject
    public RepositoryModelDataMapper() {
    }

    @NonNull
    @Override
    public RepositoryModel transform(@NonNull Repository repository) {
        return new RepositoryModel(repository.getId(),
                repository.getName(),
                repository.getDescription(),
                repository.getLanguage(),
                repository.getStargazers(),
                repository.getForks(),
                repository.getUpdate());
    }
}
