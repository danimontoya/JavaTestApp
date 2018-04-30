package com.danieh.javatestapp.ui.main;

import android.support.annotation.NonNull;

import com.danieh.domain.model.Repository;
import com.danieh.domain.usecases.DefaultObserver;
import com.danieh.domain.usecases.LoadRepositoriesUseCase;
import com.danieh.javatestapp.ui.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.danieh.data.net.RestApi.ITEMS_PER_PAGE;

/**
 * Created by danieh
 */

public class MainPresenter extends BasePresenter<MainView> implements MainView.Listener {

    protected List<Repository> repositories = new ArrayList<>();

    private int pageCount = 1;

    @NonNull
    private LoadRepositoriesUseCase loadRepositoriesUseCase;

    @Inject
    public MainPresenter(@NonNull MainView view, @NonNull final LoadRepositoriesUseCase loadRepositoriesUseCase) {
        super(view);
        this.loadRepositoriesUseCase = loadRepositoriesUseCase;
    }

    @Override
    public void onInitView() {
        if (view == null) return;
        view.showLoading();
        loadRepositoriesUseCase.execute(new RepositoryListObserver(), pageCount);
    }

    @Override
    public void onLoadMore() {
        if (view == null) return;
        view.showLoadingItem();
        loadRepositoriesUseCase.execute(new RepositoryListObserver(), pageCount);
    }

    private void onRepositoriesRetrieved(List<Repository> newRepositoryList) {
        if (view == null) return;
        if (this.repositories.isEmpty()) {
            if (newRepositoryList == null || newRepositoryList.isEmpty())
                view.showEmpty();
            else {
                this.repositories.addAll(newRepositoryList);
                view.showRepositories(this.repositories);
                pageCount++;
                if (newRepositoryList.size() > ITEMS_PER_PAGE)
                    view.removeScrollListener();
            }
        } else if (newRepositoryList != null && !newRepositoryList.isEmpty()) {
            for (Repository repo : newRepositoryList) {
                if (!repositories.contains(repo)) {
                    this.repositories.add(repo);
                    view.showNewRepository(repo);
                }
            }
            if (newRepositoryList.size() == ITEMS_PER_PAGE) {
                view.canLoadMore();
            } else {
                view.removeScrollListener();
            }
            pageCount++;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        this.loadRepositoriesUseCase.dispose();
    }

    private final class RepositoryListObserver extends DefaultObserver<List<Repository>> {

        @Override
        public void onComplete() {
            if (view == null) return;

            view.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            if (view == null) return;

            view.hideLoading();
            view.removeLoadingItem();
            view.showError(e.getMessage());
        }

        @Override
        public void onNext(List<Repository> repositories) {
            onRepositoriesRetrieved(repositories);
        }
    }
}
