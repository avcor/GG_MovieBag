package com.example.myapplication.models;

public class ProductionCompanyModel {
    String name;
    String id;
    String logo_path;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public ProductionCompanyModel(String name, String id, String logo_path) {
        this.name = name;
        this.id = id;
        this.logo_path = logo_path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }
}
