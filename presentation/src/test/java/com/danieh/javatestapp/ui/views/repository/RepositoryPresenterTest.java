package com.danieh.javatestapp.ui.views.repository;

import android.support.annotation.NonNull;

import com.danieh.javatestapp.model.RepositoryModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Created by danieh
 */
@RunWith(MockitoJUnitRunner.class)
public class RepositoryPresenterTest {

    private RepositoryPresenter presenter;

    @Mock
    private RepositoryView view;

    @Before
    public void setUp() {
        presenter = new RepositoryPresenter(view);
    }

    @Test
    public void onBindView() {

        RepositoryModel repositoryModel = createRepositoryModel();
        presenter.onBindView(repositoryModel, 0);

        verify(view).showTitle("#" + 0 + ": " + repositoryModel.getName());
        verify(view).showDescription(repositoryModel.getDescription());
        verify(view).showLanguage(repositoryModel.getLanguage() != null ? repositoryModel.getLanguage() : "");
        verify(view).showStars(String.valueOf(repositoryModel.getStargazers()));
        verify(view).showForks(String.valueOf(repositoryModel.getForks()));
    }

    @NonNull
    private RepositoryModel createRepositoryModel() {
        return new RepositoryModel(123,
                "Titlee", "this is the description", "Kotlin", 100, 34, anyString());
    }

}