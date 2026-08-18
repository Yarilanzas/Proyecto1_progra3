package org.domain;


public class Resource {
    private Category category;
    private String id;
    private String description;

    public Resource() {
        this.description = "";
        this.category = null;
        this.id = "";
    }

    public Resource(String description, Category category, String id) {
        this.description = description;
        this.category = category;
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }
}