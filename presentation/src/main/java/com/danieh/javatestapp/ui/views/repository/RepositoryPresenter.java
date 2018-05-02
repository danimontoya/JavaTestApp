package com.danieh.javatestapp.ui.views.repository;

import android.support.annotation.NonNull;

import com.danieh.javatestapp.model.RepositoryModel;

/**
 * Created by danieh
 */

public class RepositoryPresenter implements RepositoryView.Listener {

    private RepositoryView view;

    public RepositoryPresenter(@NonNull RepositoryView view) {
        this.view = view;
    }

    @Override
    public void onBindView(@NonNull RepositoryModel repository, int adapterPosition) {
        if (view == null) return;

        view.showTitle("#" + adapterPosition + ": " + repository.getName());
//        view.showTitle(repository.getName());
        view.showDescription(repository.getDescription());

        view.showLanguage(repository.getLanguage() != null ? repository.getLanguage() : "");
        view.showStars(String.valueOf(repository.getStargazers()));
        view.showForks(String.valueOf(repository.getForks()));
    }
}
