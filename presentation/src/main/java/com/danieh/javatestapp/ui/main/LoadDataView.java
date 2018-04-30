package com.danieh.javatestapp.ui.main;

import android.content.Context;

/**
 * Created by danieh
 */
public interface LoadDataView {

    void showLoading();

    void hideLoading();

    void showError(String message);

    Context context();

    void showEmpty();
}
