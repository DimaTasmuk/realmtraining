package com.tasmuk.realmtraining.retrofit;

import com.tasmuk.realmtraining.models.GitHubRepos;
import com.tasmuk.realmtraining.models.GitHubUser;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


interface GitHubAPI {

    @GET("/users/{username}")
    Observable<GitHubUser> user(@Path("username") String username);

    @GET("/users/{username}/repos")
    Observable<ArrayList<GitHubRepos>> userRepos(@Path("username") String username);
}
