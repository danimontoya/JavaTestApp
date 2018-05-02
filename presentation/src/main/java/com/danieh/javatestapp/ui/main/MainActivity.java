package com.danieh.javatestapp.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.danieh.javatestapp.R;
import com.danieh.javatestapp.databinding.ActivityMainBinding;
import com.danieh.javatestapp.model.RepositoryModel;
import com.danieh.javatestapp.ui.BaseActivity;
import com.danieh.javatestapp.ui.main.adapter.RepoAdapter;
import com.danieh.javatestapp.ui.main.adapter.RepoItemDecoration;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by danieh
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private final static int ITEMS_BEFORE_RELOAD = 3;
    @Inject
    MainPresenter presenter;
    private ActivityMainBinding binding;
    private RepoAdapter adapter;
    private RecyclerView.OnScrollListener scrollListener;
    private boolean loadingNewItems = false;

    @NonNull
    @Override
    protected MainPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.progressBar.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        presenter.onInitView();
    }

    @Override
    public void showRepositories(@NonNull List<RepositoryModel> repositories) {
        adapter = new RepoAdapter(this, repositories);
        adapter.setHasStableIds(true);
        binding.repoRecyclerview.addItemDecoration(new RepoItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL, R.drawable.divider));
        binding.repoRecyclerview.setAdapter(adapter);
        adapter.onAttachedToRecyclerView(binding.repoRecyclerview);
        scrollListener = new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                Log.d("ApiConnection", "ITEMS_BEFORE_RELOAD=" + ITEMS_BEFORE_RELOAD + ", ItemCount=" + recyclerView.getAdapter().getItemCount() +
                        ", lastVisibleItemPosition=" + lastVisibleItemPosition);
                boolean isTimeToLoadMoreItems = ITEMS_BEFORE_RELOAD >= recyclerView.getAdapter().getItemCount() - lastVisibleItemPosition;
                if (!loadingNewItems && isTimeToLoadMoreItems) {
                    loadingNewItems = true;
                    presenter.onLoadMore();
                }
            }
        };
        binding.repoRecyclerview.addOnScrollListener(scrollListener);
    }

    @Override
    public void showLoadingItem() {
        if (adapter != null) {
            adapter.addLoadView();
            binding.repoRecyclerview.smoothScrollToPosition(adapter.getItemCount());
        }
    }

    @Override
    public void removeLoadingItem() {
        if (adapter != null)
            adapter.removeLoadView();
    }

    @Override
    public void showNewRepository(RepositoryModel repository) {
        if (adapter != null)
            adapter.addRepository(repository);
    }

    @Override
    public void canLoadMore() {
        loadingNewItems = false;
    }

    @Override
    public void removeScrollListener() {
        binding.repoRecyclerview.removeOnScrollListener(scrollListener);
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showEmpty() {
        binding.repoRecyclerview.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);
        binding.emptyLayout.setVisibility(View.VISIBLE);
    }
}
