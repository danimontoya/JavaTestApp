package com.danieh.javatestapp.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by danieh
 */

public class RepositoryModel {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @Nullable
    private String language;
    private int stargazers;
    private int forks;
    @NonNull
    private String update;

    public RepositoryModel(@NonNull Integer id, @NonNull String name, @NonNull String description, @Nullable String language, int stargazers, int forks, @NonNull String update) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.language = language;
        this.stargazers = stargazers;
        this.forks = forks;
        this.update = update;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @Nullable
    public String getLanguage() {
        return language;
    }

    public void setLanguage(@NonNull String language) {
        this.language = language;
    }

    public int getStargazers() {
        return stargazers;
    }

    public void setStargazers(int stargazers) {
        this.stargazers = stargazers;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    @NonNull
    public String getUpdate() {
        return update;
    }

    public void setUpdate(@NonNull String update) {
        this.update = update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepositoryModel that = (RepositoryModel) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + stargazers;
        result = 31 * result + forks;
        result = 31 * result + update.hashCode();
        return result;
    }
}
