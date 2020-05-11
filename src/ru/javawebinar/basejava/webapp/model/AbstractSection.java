package ru.javawebinar.basejava.webapp.model;

abstract class AbstractSection implements Section {
    private final String title;

    AbstractSection(String title) {
        this.title = title;
    }

    @Override
    public String getTitle(){
        return title;
    }
}
