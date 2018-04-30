package com.danieh.data.entitiy.mapper;

import com.danieh.data.entitiy.RepositoryEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class RepositoryEntityJsonMapper {

    private final Gson gson;

    @Inject
    public RepositoryEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<RepositoryEntity> transformRepositoryEntityCollection(String repoListJsonResponse)
            throws JsonSyntaxException {
        final Type listOfRepositoryEntityType = new TypeToken<List<RepositoryEntity>>() {
        }.getType();
        return this.gson.fromJson(repoListJsonResponse, listOfRepositoryEntityType);
    }
}
