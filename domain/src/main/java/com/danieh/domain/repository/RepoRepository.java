package com.danieh.domain.repository;

import com.danieh.domain.model.Repository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a Repository for getting {@link Repository} related data.
 * Funny that repository pattern has to get 'Repository' model
 */
public interface RepoRepository {

    Observable<List<Repository>> repositories(Integer pageCount);
}
