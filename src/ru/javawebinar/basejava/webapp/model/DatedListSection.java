package ru.javawebinar.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class DatedListSection extends AbstractSection {
    private final List<DatedListSectionRecord> datedListSectionRecords = new ArrayList<>();

    public DatedListSection(String title) {
        super(title);
    }

    public void add(DatedListSectionRecord datedListSectionRecord) {
        datedListSectionRecords.add(datedListSectionRecord);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DatedListSectionRecord record : datedListSectionRecords) {
            sb.append(record.toString());
        }
        return sb.toString();
    }
}