package com.danieh.javatestapp.ui.main;

import com.danieh.domain.usecases.LoadRepositoriesUseCase;
import com.danieh.javatestapp.mapper.RepositoryModelDataMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


/**
 * Created by danieh
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private MainPresenter presenter;

    @Mock
    private MainView view;
    @Mock
    private LoadRepositoriesUseCase loadRepositoriesUseCase;
    @Mock
    private RepositoryModelDataMapper repositoryModelDataMapper;

    @Before
    public void setUp() {
        presenter = new MainPresenter(view, loadRepositoriesUseCase, repositoryModelDataMapper);
    }

    @Test
    public void testOnInitView() {
        int pageCount = 1;
        presenter.setPageCount(pageCount);

        presenter.onInitView();

        verify(view).showLoading();
        verify(loadRepositoriesUseCase).execute(any(DisposableObserver.class), eq(pageCount));
    }

    @Test
    public void testOnLoadMore() {
        int pageCount = 2;
        presenter.setPageCount(pageCount);

        presenter.onLoadMore();

        verify(view).showLoadingItem();
        verify(loadRepositoriesUseCase).execute(any(DisposableObserver.class), eq(pageCount));
    }

}