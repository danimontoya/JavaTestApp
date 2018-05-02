package com.danieh.domain.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Transforms Data entity to Domain model and Domain model to Presentation model.
 *
 * @param <DA> Domain model type
 * @param <DO> Presentation model type
 */
public abstract class DataMapper<DA, DO> {

    @NonNull
    public abstract DO transform(@NonNull DA e);

    @NonNull
    public List<DO> transform(@NonNull Collection<DA> dataEntities) {
        final List<DO> result = new ArrayList<>();

        for (DA entity : dataEntities) {
            result.add(transform(entity));
        }

        return result;
    }

}
