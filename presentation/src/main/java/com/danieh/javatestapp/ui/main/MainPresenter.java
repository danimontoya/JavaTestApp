package com.danieh.javatestapp.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.danieh.domain.model.Repository;
import com.danieh.domain.usecases.DefaultObserver;
import com.danieh.domain.usecases.LoadRepositoriesUseCase;
import com.danieh.javatestapp.mapper.RepositoryModelDataMapper;
import com.danieh.javatestapp.model.RepositoryModel;
import com.danieh.javatestapp.ui.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.danieh.data.net.RestApi.ITEMS_PER_PAGE;

/**
 * Created by danieh
 */

public class MainPresenter extends BasePresenter<MainView> implements MainView.Listener {

    private List<RepositoryModel> repositories = new ArrayList<>();

    private int pageCount = 1;

    @NonNull
    private LoadRepositoriesUseCase loadRepositoriesUseCase;

    @NonNull
    private RepositoryModelDataMapper repositoryModelDataMapper;

    @Inject
    public MainPresenter(@NonNull MainView view, @NonNull final LoadRepositoriesUseCase loadRepositoriesUseCase, @NonNull RepositoryModelDataMapper repositoryModelDataMapper) {
        super(view);
        this.loadRepositoriesUseCase = loadRepositoriesUseCase;
        this.repositoryModelDataMapper = repositoryModelDataMapper;
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

    @Override
    public void destroy() {
        super.destroy();
        loadRepositoriesUseCase.dispose();
    }

    private void onRepositoriesRetrieved(List<Repository> repositoryList) {
        if (view == null) return;

        if (repositories.isEmpty() && (repositoryList == null || repositoryList.isEmpty())) {
            view.showEmpty();

        } else if (repositoryList != null && !repositoryList.isEmpty()) {

            List<RepositoryModel> repositoryModelList = repositoryModelDataMapper.transform(repositoryList);
            if (repositories.isEmpty()) {
                repositories.addAll(repositoryModelList);
                view.showRepositories(repositoryModelList);
            } else {
                for (RepositoryModel repositoryModel : repositoryModelList) {
                    if (!repositories.contains(repositoryModel)) {
                        view.showNewRepository(repositoryModel);
                        repositories.add(repositoryModel);
                    }
                }
            }

            if (repositoryList.size() == ITEMS_PER_PAGE) {
                view.canLoadMore();
            } else {
                view.removeScrollListener();
            }
            setPageCount(pageCount + 1);
        }
    }

    @VisibleForTesting
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
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
        public void onNext(List<Repository> repositoryList) {
            onRepositoriesRetrieved(repositoryList);
        }
    }
}
