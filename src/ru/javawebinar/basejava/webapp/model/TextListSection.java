package ru.javawebinar.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class TextListSection extends AbstractSection {
    List<String> textLines = new ArrayList<>();
    TextListSection(String title) {
        super(title);
    }

    public List<String> getTextLines() {
        return textLines;
    }

    public void addLine(String line){
        textLines.add(line);
    }

    @Override
    public void write() {
        for (String line: textLines) {
            System.out.println(line);
        }
    }
}
