package com.danieh.data.repository.datasource;

import com.danieh.data.ApplicationTestCase;
import com.danieh.data.cache.RepositoryCache;
import com.danieh.data.net.RestApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

public class RepoDataStoreFactoryTest extends ApplicationTestCase {

    private RepoDataStoreFactory repoDataStoreFactory;

    @Mock
    private RepositoryCache repositoryCache;

    @Mock
    private RestApi restApi;

    @Before
    public void setUp() {
        repoDataStoreFactory = new RepoDataStoreFactory(RuntimeEnvironment.application, repositoryCache, restApi);
    }

    @Test
    public void testCreateDiskDataStore() {
        given(restApi.isNetworkAvailable()).willReturn(false);
        given(repositoryCache.isExpired()).willReturn(false);
        given(repositoryCache.isCached()).willReturn(true);

        RepoDataStore repoDataStore = repoDataStoreFactory.create();

        assertThat(repoDataStore, is(notNullValue()));
        assertThat(repoDataStore, is(instanceOf(DiskRepoDataStore.class)));
    }

    @Test
    public void testCreateCloudDataStore() {
        given(restApi.isNetworkAvailable()).willReturn(true);
        given(repositoryCache.isExpired()).willReturn(false);
        given(repositoryCache.isCached()).willReturn(true);

        RepoDataStore repoDataStore = repoDataStoreFactory.create();

        assertThat(repoDataStore, is(notNullValue()));
        assertThat(repoDataStore, is(instanceOf(CloudRepoDataStore.class)));
    }
}
