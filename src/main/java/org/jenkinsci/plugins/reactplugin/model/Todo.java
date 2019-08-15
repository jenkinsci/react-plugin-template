package org.jenkinsci.plugins.reactplugin.model;

import net.sf.json.JSONObject;

public class Todo {
    String name;

    private static final String KEY_NAME = "name";

    public Todo(String name) {
        this.name = name;
    }

    public Todo(JSONObject jsonObject) {
        this.name = jsonObject.getString(KEY_NAME);
    }

    public String getName() {
        return name;
    }
}
