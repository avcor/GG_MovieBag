package com.example.myapplication.models;

public class CastModel {
    String id;
    String known_for_department;
    String name;
    String character;
    String profile_path;

    public CastModel(String id, String known_for_department, String name, String character, String profile_path) {
        this.id = id;
        this.known_for_department = known_for_department;
        this.name = name;
        this.character = character;
        this.profile_path = profile_path;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public String getId() {
        return id;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public String getName() {
        return name;
    }

    public String getCharacter() {
        return character;
    }
}
