package com.danieh.javatestapp.ui.views.repository;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.danieh.javatestapp.R;
import com.danieh.javatestapp.databinding.LayoutRepoItemBinding;
import com.danieh.javatestapp.model.RepositoryModel;

/**
 * Created by danieh
 */

public class RepositoryLayout extends ConstraintLayout implements RepositoryView {

    @NonNull
    private RepositoryPresenter presenter;

    private LayoutRepoItemBinding binding;

    public RepositoryLayout(Context context) {
        this(context, null);
    }

    public RepositoryLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RepositoryLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        presenter = new RepositoryPresenter(this);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.layout_repo_item, this, true);
    }

    public void bindView(@NonNull RepositoryModel repository, int adapterPosition) {
        presenter.onBindView(repository, adapterPosition);
    }

    @Override
    public void showTitle(@NonNull String name) {
        binding.titleTextview.setText(name);
    }

    @Override
    public void showDescription(@NonNull String desc) {
        binding.descriptionTextview.setText(desc);
    }

    @Override
    public void showLanguage(@NonNull String language) {
        binding.languageTextview.setText(language);
        binding.languageTextview.setVisibility(VISIBLE);
    }

    @Override
    public void hideLanguage() {
        binding.languageTextview.setVisibility(INVISIBLE);
    }

    @Override
    public void showStars(@NonNull String stars) {
        binding.starsTextview.setText(stars);
    }

    @Override
    public void showForks(@NonNull String forks) {
        binding.forksTextview.setText(forks);
    }
}
