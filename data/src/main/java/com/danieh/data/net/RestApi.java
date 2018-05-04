package com.danieh.data.net;

import com.danieh.data.entitiy.RepositoryEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {

    String API_BASE_URL = "https://api.github.com/users/JakeWharton/repos?";

    int ITEMS_PER_PAGE = 15;

    String API_URL_GET_USER_LIST = API_BASE_URL + "page=%s&per_page=" + ITEMS_PER_PAGE;


    Observable<List<RepositoryEntity>> repoEntityList(Integer pageCount);

    boolean isNetworkAvailable();
}
