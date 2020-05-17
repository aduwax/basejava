package ru.javawebinar.basejava.webapp.model;

public class TextSection extends AbstractSection {
    private String text;

    public TextSection(String title) {
        super(title);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}