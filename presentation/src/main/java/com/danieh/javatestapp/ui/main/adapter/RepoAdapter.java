package com.danieh.javatestapp.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.danieh.domain.model.Repository;
import com.danieh.javatestapp.R;
import com.danieh.javatestapp.ui.views.repository.RepositoryLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_ID;

/**
 * Created by danieh
 */

public class RepoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int REPO_ITEM = 1;
    private static final int LOAD_ITEM = 2;

    private int loadingItem = 0;
    @NonNull
    private List<Repository> repositories;
    @NonNull
    private RecyclerView recyclerView;
    @NonNull
    private Context context;

    public RepoAdapter(@NonNull Context context, @NonNull final List<Repository> repositories) {
        this.context = context;
        this.repositories = repositories;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case REPO_ITEM:
                return new RepoViewHolder(new RepositoryLayout(context));
            case LOAD_ITEM:
                return new LoadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_load_item, parent, false));
            default:
                throw new IllegalArgumentException("Unknown view type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case REPO_ITEM:
                ((RepoViewHolder) holder).bind(repositories.get(position));
                break;
            case LOAD_ITEM:
                break;
            default:
                throw new IllegalArgumentException("Unknown view type: " + getItemViewType(position));
        }

    }

    @Override
    public int getItemCount() {
        return repositories.size() + loadingItem;
    }

    @Override
    public int getItemViewType(final @ItemType int position) {
        if (position < repositories.size())
            return REPO_ITEM;
        else {
            return LOAD_ITEM;
        }
    }

    @Override
    public long getItemId(final int position) {
        switch (getItemViewType(position)) {
            case REPO_ITEM:
                return repositories.get(position).getId().longValue();
            case LOAD_ITEM:
                return NO_ID;
        }
        return 0;
    }

    public void addLoadView() {
        if (loadingItem == 1) return;
        // Show loading view
        loadingItem = 1;
        recyclerView.post(() -> notifyItemInserted(getItemCount() - 1));
    }

    public void removeLoadView() {
        if (loadingItem == 0) return;
        // hide loading view
        loadingItem = 0;
        recyclerView.post(() -> notifyItemRemoved(getItemCount() - 1));
    }

    public void addRepository(@NonNull Repository repository) {
        // hide loading view
        loadingItem = 0;
        this.repositories.add(repository);
        recyclerView.post(() -> notifyItemInserted(getItemCount() - 1));
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({REPO_ITEM, LOAD_ITEM})
    public @interface ItemType {
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {

        final RepositoryLayout view;

        RepoViewHolder(View view) {
            super(view);
            this.view = (RepositoryLayout) view;
        }

        public void bind(final Repository item) {
            view.bindView(item, getAdapterPosition());
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        final ProgressBar progressBar;

        LoadingViewHolder(View view) {
            super(view);
            this.progressBar = view.findViewById(R.id.load_progressbar);
            this.progressBar.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        }
    }

}
