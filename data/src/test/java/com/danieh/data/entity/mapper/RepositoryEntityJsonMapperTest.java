package com.danieh.data.entity.mapper;

import com.danieh.data.entitiy.RepositoryEntity;
import com.danieh.data.entitiy.mapper.RepositoryEntityJsonMapper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryEntityJsonMapperTest {

    private static final String JSON_RESPONSE_REPOSITORY_COLLECTION = "[" +
            "{\"id\":3070104," +
            "\"name\":\"abs.io\"," +
            "\"description\":\"Simple URL shortener for ActionBarSherlock using node.js and express.\"," +
            "\"updated_at\":\"2018-04-19T15:12:39Z\"," +
            "\"size\":108," +
            "\"stargazers_count\":7," +
            "\"language\":\"JavaScript\"," +
            "\"forks\":1" +
            "}," +
            "{\"id\":1451060," +
            "\"name\":\"ActionBarSherlock\"," +
            "\"description\":\"[DEPRECATED] Action bar implementation which uses the native action bar on Android 4.0+ and a custom implementation on pre-4.0 through a single API and theme.\"," +
            "\"updated_at\":\"2018-05-03T09:58:10Z\"," +
            "\"size\":21244," +
            "\"stargazers_count\":7314," +
            "\"language\":\"Java\"," +
            "\"forks\":3945}]";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private RepositoryEntityJsonMapper repositoryEntityJsonMapper;

    @Before
    public void setUp() {
        repositoryEntityJsonMapper = new RepositoryEntityJsonMapper();
    }

    @Test
    public void testTransformUserEntityCollectionHappyCase() {
        Collection<RepositoryEntity> repositoryEntityCollection =
                repositoryEntityJsonMapper.transformRepositoryEntityCollection(
                        JSON_RESPONSE_REPOSITORY_COLLECTION);

        assertThat(((RepositoryEntity) repositoryEntityCollection.toArray()[0]).getId(), is(3070104));
        assertThat(((RepositoryEntity) repositoryEntityCollection.toArray()[1]).getId(), is(1451060));
        assertThat(repositoryEntityCollection.size(), is(2));
    }
}
