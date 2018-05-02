package com.danieh.javatestapp.ui.main;

import android.support.annotation.NonNull;

import com.danieh.javatestapp.model.RepositoryModel;

import java.util.List;

/**
 * Created by danieh
 */

public interface MainView extends LoadDataView {

    void showRepositories(@NonNull List<RepositoryModel> repositories);

    void showLoadingItem();

    void removeLoadingItem();

    void showNewRepository(RepositoryModel repository);

    void canLoadMore();

    void removeScrollListener();

    interface Listener {

        void onInitView();

        void onLoadMore();
    }

}
