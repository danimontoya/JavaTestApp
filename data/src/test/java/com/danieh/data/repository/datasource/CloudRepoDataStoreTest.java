package com.danieh.data.repository.datasource;

import com.danieh.data.cache.RepositoryCache;
import com.danieh.data.entitiy.RepositoryEntity;
import com.danieh.data.net.RestApi;

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
public class CloudRepoDataStoreTest {

    private CloudRepoDataStore cloudRepoDataStore;

    @Mock
    private RestApi restApi;

    @Mock
    private RepositoryCache repositoryCache;

    @Before
    public void setUp() {
        cloudRepoDataStore = new CloudRepoDataStore(restApi, repositoryCache);
    }

    @Test
    public void testGetUserEntityListFromApi() {
        final int pageCount = 1;

        List<RepositoryEntity> repositoryEntityList = new ArrayList<>();
        Observable<List<RepositoryEntity>> fakeObservable = Observable.just(repositoryEntityList);
        given(restApi.repoEntityList(pageCount)).willReturn(fakeObservable);

        cloudRepoDataStore.repoEntityList(pageCount);
        verify(restApi).repoEntityList(pageCount);
    }
}
