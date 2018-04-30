package com.danieh.javatestapp.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

public abstract class BasePresenter<V> {

    @Nullable
    protected V view;

    public BasePresenter(@NonNull V view) {
        this.view = view;
    }

    public void start() {
    }

    public void attach() {
    }

    @CallSuper
    public void stop() {
    }

    @CallSuper
    public void destroy() {
        view = null;
    }

    @VisibleForTesting
    @Nullable
    public V getView() {
        return view;
    }

}
