package com.danieh.data.repository.datasource;

import com.danieh.data.cache.RepositoryCache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DiskRepoDataStoreTest {

    private DiskRepoDataStore diskRepoDataStore;

    @Mock
    private RepositoryCache repositoryCache;

    @Before
    public void setUp() {
        diskRepoDataStore = new DiskRepoDataStore(repositoryCache);
    }

    @Test
    public void testGetRepositoryEntitiesList() {
        diskRepoDataStore.repoEntityList(1);
        verify(repositoryCache).get();
    }
}
