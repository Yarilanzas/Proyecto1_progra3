package org.domain;

public class Category {
    private String id;
    private String description;

    public Category() {
        this.id = "";
        this.description = "";

    }
    public Category(String id, String description) {
        this.id = id;
        this.description = description;

    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}