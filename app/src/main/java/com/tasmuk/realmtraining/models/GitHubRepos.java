package com.tasmuk.realmtraining.models;


public class GitHubRepos {

    private String name;
    private String description;
    private String language;
    private String pushed_at;
    private String created_at;
    private String updated_at;
    private Boolean has_projects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getHas_projects() {
        return has_projects;
    }

    public void setHas_projects(Boolean has_projects) {
        this.has_projects = has_projects;
    }

    public String showShortInfo() {
        return String.format("%s (%s)", name, language);
    }

    @Override
    public String toString() {
        return showShortInfo();
//        return "GitHubRepos{" +
//                "name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", language='" + language + '\'' +
//                ", pushed_at='" + pushed_at + '\'' +
//                ", created_at='" + created_at + '\'' +
//                ", updated_at='" + updated_at + '\'' +
//                ", has_projects=" + has_projects +
//                '}';
    }
}
