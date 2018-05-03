package com.danieh.domain.usecases;

import com.danieh.domain.executor.PostExecutionThread;
import com.danieh.domain.executor.ThreadExecutor;
import com.danieh.domain.repository.RepoRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by danieh
 */
@RunWith(MockitoJUnitRunner.class)
public class LoadRepositoriesUseCaseTest {

    private LoadRepositoriesUseCase loadRepositoriesUseCase;

    @Mock
    private ThreadExecutor threadExecutor;
    @Mock
    private PostExecutionThread postExecutionThread;
    @Mock
    private RepoRepository repoRepository;

    @Before
    public void setUp() {
        loadRepositoriesUseCase = new LoadRepositoriesUseCase(threadExecutor, postExecutionThread, repoRepository);
    }

    @Test
    public void testLoadRepositoriesUseCaseObservableHappyCase() {
        final int pageCount = 1;
        loadRepositoriesUseCase.buildUseCaseObservable(pageCount);

        verify(repoRepository).repositories(pageCount);
        verifyNoMoreInteractions(repoRepository);
        verifyZeroInteractions(threadExecutor);
        verifyZeroInteractions(postExecutionThread);
    }


}
