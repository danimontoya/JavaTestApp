package com.danieh.javatestapp.ui.main;

import android.support.annotation.NonNull;

import com.danieh.domain.model.Repository;

import java.util.List;

/**
 * Created by danieh
 */

public interface MainView extends LoadDataView {

    void showRepositories(@NonNull List<Repository> repositories);

    void showLoadingItem();

    void removeLoadingItem();

    void showNewRepository(Repository repository);

    void canLoadMore();

    void removeScrollListener();

    interface Listener {

        void onInitView();

        void onLoadMore();
    }

}
