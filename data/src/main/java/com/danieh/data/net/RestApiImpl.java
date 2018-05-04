package com.danieh.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.danieh.data.entitiy.RepositoryEntity;
import com.danieh.data.entitiy.mapper.RepositoryEntityJsonMapper;
import com.danieh.data.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
@Singleton
public class RestApiImpl implements RestApi {

    private final Context context;
    private final RepositoryEntityJsonMapper repoEntityJsonMapper;

    @Inject
    RestApiImpl(Context context, RepositoryEntityJsonMapper repoEntityJsonMapper) {
        if (context == null || repoEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.repoEntityJsonMapper = repoEntityJsonMapper;
    }

    @Override
    public Observable<List<RepositoryEntity>> repoEntityList(final Integer pageCount) {
        return Observable.create(emitter -> {
            if (isNetworkAvailable()) {
                try {
                    String responseUserEntities = getRepoEntitiesFromApi(pageCount);
                    if (responseUserEntities != null) {
                        emitter.onNext(repoEntityJsonMapper.transformRepositoryEntityCollection(responseUserEntities));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException("No internet connection"));
            }
        });
    }

    private String getRepoEntitiesFromApi(Integer pageCount) throws MalformedURLException {
        return ApiConnection.createGET(String.format(API_URL_GET_USER_LIST, String.valueOf(pageCount))).requestSyncCall();
    }

    @Override
    public boolean isNetworkAvailable() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
