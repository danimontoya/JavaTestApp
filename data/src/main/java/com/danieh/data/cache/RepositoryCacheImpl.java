package com.danieh.data.cache;

import android.support.annotation.NonNull;

import com.danieh.data.entitiy.RepositoryEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * {@link RepositoryCache} implementation.
 */
@Singleton
public class RepositoryCacheImpl implements RepositoryCache {

    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());

    @Inject
    RepositoryCacheImpl() {
    }

    @Override
    public boolean isExpired() {
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(RepositoryEntity.class).count() != 0) {
            Date currentTime = new Date(System.currentTimeMillis());
            Date lastUpdated = null;
            try {
                String updatedAt = realm.where(RepositoryEntity.class).findFirst().getUpdatedAt();
                lastUpdated = ISO8601DATEFORMAT.parse(updatedAt);
                boolean isExpired = currentTime.getTime() - lastUpdated.getTime() > EXPIRATION_TIME;
                if (isExpired) {
                    realm.beginTransaction();
                    realm.delete(RepositoryEntity.class);
                    realm.commitTransaction();
                    realm.close();
                }
                return isExpired;
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                realm.close();
            }

        }
        realm.close();
        return false;
    }

    @Override
    public boolean isCached() {
        Realm realm = Realm.getDefaultInstance();
        boolean isCached = realm.where(RepositoryEntity.class).findAll() != null && realm.where(RepositoryEntity.class).findAll().size() > 0;
        realm.close();
        return isCached;
    }

    @Override
    public Observable<List<RepositoryEntity>> get() {
        Realm realm = Realm.getDefaultInstance();
        List<RepositoryEntity> repositoryEntities = realm.where(RepositoryEntity.class).findAll();
        List<RepositoryEntity> repositoryEntitiesMainThread = getRepositoryEntitiesForMainThread(realm, repositoryEntities);
        return Observable.just(repositoryEntitiesMainThread);
    }

    @Override
    public void put(List<RepositoryEntity> repositoryEntities) {
        for (RepositoryEntity repoEntity : repositoryEntities) {
            repoEntity.setUpdatedAt(ISO8601DATEFORMAT.format(new Date(System.currentTimeMillis())));
        }
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(repositoryEntities);
        realm.commitTransaction();
        realm.close();
    }

    @NonNull
    private List<RepositoryEntity> getRepositoryEntitiesForMainThread(Realm realm, List<RepositoryEntity> repositoryEntities) {
        List<RepositoryEntity> repositoryEntitiesMainThread = new ArrayList<>();
        for (RepositoryEntity repo : repositoryEntities) {
            repositoryEntitiesMainThread.add(realm.copyFromRealm(repo));
        }
        realm.close();
        return repositoryEntitiesMainThread;
    }
}
