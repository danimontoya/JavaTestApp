package com.danieh.domain.usecases;

import android.support.annotation.NonNull;

import com.danieh.domain.executor.PostExecutionThread;
import com.danieh.domain.executor.ThreadExecutor;
import com.danieh.domain.model.Repository;
import com.danieh.domain.repository.RepoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by danieh
 */

public class LoadRepositoriesUseCase extends UseCase<List<Repository>, Integer> {

    @NonNull
    private RepoRepository repoRepository;

    @Inject
    LoadRepositoriesUseCase(@NonNull ThreadExecutor threadExecutor, @NonNull PostExecutionThread postExecutionThread, @NonNull final RepoRepository repoRepository) {
        super(threadExecutor, postExecutionThread);
        this.repoRepository = repoRepository;
    }

    @Override
    Observable<List<Repository>> buildUseCaseObservable(Integer pageCount) {
        return repoRepository.repositories(pageCount);
    }
}
