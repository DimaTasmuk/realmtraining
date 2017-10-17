package com.tasmuk.realmtraining.models;


public class GitHubUser {

    private String name;
    private String email;
    private Integer public_repos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPublicRepos() {
        return public_repos;
    }

    public void setPublicRepos(Integer publicRepos) {
        this.public_repos = publicRepos;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", public_repos=" + public_repos +
                '}';
    }
}
