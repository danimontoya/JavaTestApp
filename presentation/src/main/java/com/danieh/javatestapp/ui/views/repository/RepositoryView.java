package com.danieh.javatestapp.ui.views.repository;

import android.support.annotation.NonNull;

import com.danieh.domain.model.Repository;

/**
 * Created by danieh
 */

public interface RepositoryView {

    void showTitle(@NonNull String name);

    void showDescription(@NonNull String desc);

    void showLanguage(@NonNull String language);

    void hideLanguage();

    void showStars(@NonNull String stars);

    void showForks(@NonNull String forks);

    interface Listener {

        void onBindView(@NonNull Repository repository, int adapterPosition);

    }
}
