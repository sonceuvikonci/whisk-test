package com.whisk.models;

import com.google.gson.annotations.Expose;
import org.json.JSONObject;

public class ShoppingList {

    @Expose(serialize = false)
    private String id;
    private String name;
    private boolean primary;
    @Expose(serialize = false)
    private JSONObject content;

    ShoppingList(String name) {
        this.name = name;
    }

    ShoppingList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public static ShoppingList createListWithName(String name) {
        return new ShoppingList(name);
    }
}
