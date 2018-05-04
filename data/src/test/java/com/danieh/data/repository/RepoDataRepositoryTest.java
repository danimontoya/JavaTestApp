package com.danieh.data.repository;

import com.danieh.data.entitiy.RepositoryEntity;
import com.danieh.data.entitiy.mapper.RepositoryEntityDataMapper;
import com.danieh.data.repository.datasource.RepoDataStore;
import com.danieh.data.repository.datasource.RepoDataStoreFactory;
import com.danieh.domain.repository.RepoRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RepoDataRepositoryTest {

    private RepoRepository repoRepository;

    @Mock
    private RepoDataStoreFactory repoDataStoreFactory;
    @Mock
    private RepositoryEntityDataMapper repositoryEntityDataMapper;
    @Mock
    private RepoDataStore repoDataStore;

    @Before
    public void setUp() {
        repoRepository = new RepoDataRepository(repoDataStoreFactory, repositoryEntityDataMapper);
        given(repoDataStoreFactory.create()).willReturn(repoDataStore);
    }

    @Test
    public void testGetRepositoriesHappyCase() {
        final int pageCount = 1;
        List<RepositoryEntity> usersList = new ArrayList<>();
        usersList.add(new RepositoryEntity());
        given(repoDataStore.repoEntityList(pageCount)).willReturn(Observable.just(usersList));

        repoRepository.repositories(pageCount);

        verify(repoDataStoreFactory).create();
        verify(repoDataStore).repoEntityList(pageCount);
    }
}
