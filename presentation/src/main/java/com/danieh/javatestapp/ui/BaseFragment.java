/*
 * Copyright © 2018 ALDI International Services GmbH & Co. oHG
 */

package com.danieh.javatestapp.ui;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    @NonNull
    protected abstract P getPresenter();

    @CallSuper
    @Override
    public void onAttach(final Context context) {
        AndroidSupportInjection.inject(this);
        getPresenter().attach();
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().start();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }
}
