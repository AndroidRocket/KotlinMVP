package com.wilson.views.adapters;

public class Section {

    private final String name;

    public boolean isExpanded=true;

    public Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
