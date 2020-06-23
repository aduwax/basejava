package ru.javawebinar.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextListSection extends AbstractSection {
    private final List<String> textLines = new ArrayList<>();

    public TextListSection(String title) {
        super(title);
    }

    public List<String> getTextLines() {
        return textLines;
    }

    public void addLine(String line) {
        textLines.add(line);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String line : textLines) {
            sb.append(line).append(" ");
        }
        return sb.toString();
    }
}